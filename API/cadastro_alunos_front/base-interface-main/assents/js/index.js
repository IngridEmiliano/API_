//O DOMContentLoaded garante que o JavaScript só execute depois que o HTML foi carregado e montado
document.addEventListener("DOMContentLoaded", () => {
 // Chama a função para buscar alunos da API assim que a página carrega
  buscarAluno();
  
  //Quando o formulário com o ID "formCadastro" for enviado, chama a função cadastrar().
  document.getElementById("formCadastro").addEventListener("submit", cadastrar);
});

        function buscarAluno(){ //cria função
    fetch("http://localhost:8080/api/aluno") //chama a API para pedir  os dados
    //tratativa pra acerto 
    //se der certo adciona linha
    .then((response) => response.json()) // pega a resposta em json e transforma em objeto javascript
    .then(data => { //recebe os dados convertidos
      atualizarTabela(data); //manda os dados para a funçao montar a tabela
    })
    // tratativa de erro
    .catch((error) => {
      console.error("Erro ao buscar por aluno: ", error);
    });
  }

  // Cria uma função chamada atualizarTabela, que recebe como parâmetro aluno
  function atualizarTabela(aluno){
   //Pega o corpo da tabela do HTML, onde as linhas dos alunos vão ser inseridas.
      const tabela = document.getElementById('tabelaCorpo'); //O elemento com o ID "tabelaCorpo"
//Limpa a tabela inteira antes de adicionar as linhas novas.
        tabela.innerHTML = "";

      //Percorre todos os alunos da lista e faz o bloco abaixo
      aluno.forEach((aluno) => { 
        //cria uma nova linha na tabela
        const tr = document.createElement("tr");
        //monta o HTML da linha do aluno + o botao
        tr.innerHTML = `
          <td class="px-4 py-2">${aluno.id}</td>
              <td class="px-4 py-2 nome">${aluno.nome}</td>
              <td class="px-4 py-2 email">${aluno.email}</td>
   
              <td class="px-4 py-2">
              <button class="bg-yellow-500 text-white px-2 py-1 rounded mr-2" onclick="editar(this, ${aluno.id})">Editar</button>
              <button class="bg-red-500 text-white px-2 py-1 rounded" onclick="remover(${aluno.id}, this)">Remover</button>
              </td>
        `;
          // Adiciona a linha na tabela, faz aparecer na tela
        tabela.appendChild(tr);
      });
    }

    //Cadastrar Novas pessoas do formulario
    function cadastrar(event){
      event.preventDefault();
      const nome = document.getElementById('nome').value;
      const email = document.getElementById('email').value;
      if(nome && email){
        
        //Limpando os campos
        document.getElementById('nome').value = "";
        document.getElementById('email').value = "";

        //API POST  - faz a requisição
        fetch('http://localhost:8080/api/aluno', { //url da api
          method: 'POST', //metodo post = enviar/criar
          headers: {
            'Content-Type': 'application/json' //informa que a requisiçao esta no formato json
          },
          // Converte o objeto {nome, email} para texto JSON antes de enviar para a API
          body: JSON.stringify({"nome":nome, "email":email})
        })
        .then(response => response.json()) // converte a resposta da API (em json) para javaSpring

        .then(data => { //recebe os dados convertidos
          buscarAluno(); //atualiza a tabela buscando os alunos
          console.log("Resposta da API:", data); //exibe a resposta
        })
        // Se deu erro na comunicação com a API, mostra o erro no console
        .catch(error => {
          console.error("Erro ao enviar dados:", error);
        });
//alerta de sucesso
          Swal.fire({
            icon: 'success',
            title: 'Sucesso!',
            text: 'Cadastro feito com sucesso'
          });
      }else{
        //alerta de erro
        Swal.fire({
          icon: 'error',
          title: 'Erro!',
          text: 'Falta dados para cadastar'
        });
      }
    }

    //Remover Alguma Linha da tabela
    function remover(id, botao){
      //confirmação para o usuário
      Swal.fire({
        icon: 'question',
        title: 'Você tem certeza?',
        showCancelButton: true,
        confirmButtonText: 'Sim',
        cancelButtonText: 'Não'
      }).then((result) => {
        // Se o usuário confirmou a exclusão
        if (result.isConfirmed) {
          // Faz uma requisição para a API para remover o aluno pelo ID
fetch(`http://localhost:8080/api/aluno/${id}`, {
  method: "DELETE", // Método DELETE
})
.then(() => {
  // Se a remoção no backend foi bem sucedida, remove a linha da tabela na tela
            const linha = botao.closest("tr");  // Encontra a linha do botão clicado
            linha.remove(); // Remove essa linha do DOM (tabela)
            // Mostra mensagem de sucesso na tela
          Swal.fire("Removido!", "Aluno excluído com sucesso.", "success");
        })
         // Caso dê erro ao tentar remover na API
        .catch((error) => {
          console.error("Erro ao remover aluno: ", error);
           Swal.fire("Erro", "Falha ao remover aluno.", "error");
        });
    }
  });
    }