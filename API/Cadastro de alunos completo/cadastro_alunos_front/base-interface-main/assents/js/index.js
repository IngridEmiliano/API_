let alunoEditandoId = null;

// Executa tudo após carregar o DOM
document.addEventListener("DOMContentLoaded", () => {
  buscarAluno();

  document.getElementById("formCadastro").addEventListener("submit", cadastrar);
  document.getElementById("cancelarEdicao").addEventListener("click", cancelarEdicao);
});

// Buscar todos os alunos
function buscarAluno() {
  fetch("http://localhost:8080/api/aluno")
    .then((response) => response.json())
    .then(data => atualizarTabela(data))
    .catch((error) => {
      console.error("Erro ao buscar por aluno: ", error);
    });
}

// Preencher tabela
function atualizarTabela(alunos) {
  const tabela = document.getElementById('tabelaCorpo');
  tabela.innerHTML = "";

  alunos.forEach((aluno) => {
    const tr = document.createElement("tr");
    tr.innerHTML = `
      <td class="px-4 py-2">${aluno.id}</td>
      <td class="px-4 py-2 nome">${aluno.nome}</td>
      <td class="px-4 py-2 email">${aluno.email}</td>
      <td class="px-4 py-2">
        <button class="bg-yellow-500 text-white px-2 py-1 rounded mr-2" onclick="editar(this, ${aluno.id})">Editar</button>
        <button class="bg-red-500 text-white px-2 py-1 rounded" onclick="remover(${aluno.id}, this)">Remover</button>
      </td>
    `;
    tabela.appendChild(tr);
  });
}

// Cadastrar ou Atualizar aluno
function cadastrar(event) {
  event.preventDefault();

  const nome = document.getElementById('nome').value;
  const email = document.getElementById('email').value;

  if (!nome || !email) {
    Swal.fire({
      icon: 'error',
      title: 'Erro!',
      text: 'Falta dados para cadastrar.'
    });
    return;
  }

  const aluno = { nome, email };

  if (alunoEditandoId === null) {
    // Criar novo aluno
    fetch('http://localhost:8080/api/aluno', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(aluno)
    })
    .then(res => res.json())
    .then(() => {
      buscarAluno();
      limparFormulario();
      Swal.fire('Sucesso!', 'Cadastro feito com sucesso.', 'success');
    })
    .catch(error => {
      console.error("Erro ao cadastrar:", error);
      Swal.fire('Erro!', 'Falha ao cadastrar aluno.', 'error');
    });

  } else {
    // Atualizar aluno existente
    fetch(`http://localhost:8080/api/aluno/${alunoEditandoId}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(aluno)
    })
    .then(res => res.json())
    .then(() => {
      buscarAluno();
      limparFormulario();
      Swal.fire('Sucesso!', 'Aluno atualizado com sucesso.', 'success');
    })
    .catch(error => {
      console.error("Erro ao atualizar:", error);
      Swal.fire('Erro!', 'Falha ao atualizar aluno.', 'error');
    });
  }
}

// Remover aluno
function remover(id, botao) {
  Swal.fire({
    icon: 'question',
    title: 'Você tem certeza?',
    showCancelButton: true,
    confirmButtonText: 'Sim',
    cancelButtonText: 'Não'
  }).then((result) => {
    if (result.isConfirmed) {
      fetch(`http://localhost:8080/api/aluno/${id}`, {
        method: "DELETE",
      })
      .then(() => {
        const linha = botao.closest("tr");
        linha.remove();
        Swal.fire("Removido!", "Aluno excluído com sucesso.", "success");
      })
      .catch((error) => {
        console.error("Erro ao remover aluno: ", error);
        Swal.fire("Erro", "Falha ao remover aluno.", "error");
      });
    }
  });
}

// Editar aluno
function editar(botao, id) {
  const linha = botao.closest("tr");
  const nome = linha.querySelector(".nome").textContent;
  const email = linha.querySelector(".email").textContent;

  document.getElementById("nome").value = nome;
  document.getElementById("email").value = email;
  alunoEditandoId = id;

  document.querySelector("button[type='submit']").textContent = "Salvar";
  document.getElementById("cancelarEdicao").classList.remove("hidden");
}

// Cancelar edição
function cancelarEdicao() {
  limparFormulario();
}

// Limpa e reseta tudo
function limparFormulario() {
  document.getElementById("formCadastro").reset();
  alunoEditandoId = null;
  document.querySelector("button[type='submit']").textContent = "Adicionar";
  document.getElementById("cancelarEdicao").classList.add("hidden");
}
