
        //API GET
    fetch('http://localhost:8080/api/alunos', {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json'
      },
    })
    .then(response => response.json())
    .then(data => {
      addlinha(data);
    })
    .catch(error => {
      console.log(error);
    });
    

  //Adicionar Linha na Tabela
  function addlinha(dadosAPI){
      const tabela = document.getElementById('tabelaCorpo');
      dadosAPI.forEach(element => {   
        const linha = document.createElement('tr');
        //Adicionando HTML
        linha.innerHTML = `
          <tr>
          <td class="px-4 py-2">${element.id}</td>
              <td class="px-4 py-2">${element.nome}</td>
              <td class="px-4 py-2">${element.email}</td>
              <td class="px-4 py-2"><button  class="bg-red-500 text-white px-2 py-1 rounded" onclick="remover(this)">remover</button></td>
          </tr>
        `;
        
        tabela.appendChild(linha);
      });
    }

    //Cadastrar Novas pessoas do formulario
    function cadastrar(){
      event.preventDefault();
      const nome = document.getElementById('nome').value;
      const email = document.getElementById('email').value;
      if(nome && email){
        //Adicionando Linha com nosso Cadastro
        this.addlinha([{"nome":nome.trim(), "email":email.trim()}]);
        
        //Limpando os campos
        document.getElementById('nome').value = "";
        document.getElementById('email').value = "";

        //API POST  
        fetch('http://localhost:8080/api/alunos', { 
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({"nome":nome, "email":email})
        })
        .then(response => response.json())
        .then(data => {
          console.log("Resposta da API:", data);
        })
        .catch(error => {
          console.error("Erro ao enviar dados:", error);
        });
    ''

          Swal.fire({
            icon: 'success',
            title: 'Sucesso!',
            text: 'Cadastro feito com sucesso'
          });
      }else{
        Swal.fire({
          icon: 'error',
          title: 'Erro!',
          text: 'Falta dados para cadastar'
        });
      }
    }

    //Remover Alguma Linha da tabela
    function remover(id, dadosbotao){ //recebe dois parametros: id pra apagar da API, dadosbotao para saber qual linha remover
     //icone de pergunta
      Swal.fire({
        icon: 'question',
        title: 'Você tem certeza?',
        showCancelButton: true,
        confirmButtonText: 'Sim',
        cancelButtonText: 'Não'
        //resposta do ususario
      }).then((result) => {
        if (result.isConfirmed) {
          //faz a requisiçao pra API (apagar)
          fetch('http://localhost:8080/api/aluno/${id}', {//ajuste do caminho
          method: 'DELETE'
        })
        //verifica se deu certo com o servidor
             .then(response => {
        if (response.ok) {
          //remove a linha da tabela
          const linharemover = dadosbotao.closest('tr'); //acha o elemento <tr> (linha) mais próximo do botão clicado.
          linharemover.remove();//apaga a linha da tabela
          //notficação de sucesso
          Swal.fire('Removido!', '', 'success');
          //caso de erro o retorno
        } else {
          Swal.fire('Erro!', 'Não foi possível remover.', 'error');
        }
      });
      //Se a pessoa clicou em "Não", aparece uma mensagem: "Cancelado".
    } else {
      Swal.fire('Cancelado', '', 'info');
    }
  });
}
