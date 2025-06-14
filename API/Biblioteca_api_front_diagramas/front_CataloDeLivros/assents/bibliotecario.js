let bibliotecarioEditandoId = null;

// Executa tudo após carregar o DOM
document.addEventListener("DOMContentLoaded", () => {
  buscarBibliotecario();

  document.getElementById("formCadastro").addEventListener("submit", cadastrar);
  document.getElementById("cancelarEdicao").addEventListener("click", cancelarEdicao);
});

// Buscar todos os bibliotecarios
function buscarBibliotecario() {
  fetch("http://localhost:8080/api/bibliotecario")
    .then((response) => response.json())
    .then(data => atualizarTabela(data))
    .catch((error) => {
      console.error("Erro ao buscar por bibliotecario: ", error);
    });
}

// Preencher tabela
function atualizarTabela(bibliotecario) {
  const tabela = document.getElementById('tabelaCorpo');
  tabela.innerHTML = "";

  bibliotecario.forEach((bibliotecario) => {
    const tr = document.createElement("tr");
    tr.innerHTML = `
      <td class="px-4 py-2">${bibliotecario.id}</td>
      <td class="px-4 py-2 nome">${bibliotecario.nome}</td>
      <td class="px-4 py-2 email">${bibliotecario.email}</td>
      <td class="px-4 py-2">
        <button class="bg-yellow-500 text-white px-2 py-1 rounded mr-2" onclick="editar(this, ${bibliotecario.id})">Editar</button>
        <button class="bg-red-500 text-white px-2 py-1 rounded" onclick="remover(${bibliotecario.id}, this)">Remover</button>
      </td>
    `;
    tabela.appendChild(tr);
  });
}

// Cadastrar ou Atualizar
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

  const bibliotecario = { nome, email };

  if (bibliotecarioEditandoId === null) {

    // Criar novo bibliotecario
    fetch('http://localhost:8080/api/bibliotecario', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(bibliotecario)
    })
    .then(res => res.json())
    .then(() => {
      buscarBibliotecario();
      limparFormulario();
      Swal.fire('Sucesso!', 'Cadastro feito com sucesso.', 'success');
    })
    .catch(error => {
      console.error("Erro ao cadastrar:", error);
      Swal.fire('Erro!', 'Falha ao cadastrar bibliotecario.', 'error');
    });

  } else {

    // Atualizar bibliotecario existente
    fetch(`http://localhost:8080/api/bibliotecario/${bibliotecarioEditandoId}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(bibliotecario)
    })
    .then(res => res.json())
    .then(() => {
      buscarBibliotecario();
      limparFormulario();
      Swal.fire('Sucesso!', 'Bibliotecario atualizado com sucesso.', 'success');
    })
    .catch(error => {
      console.error("Erro ao atualizar:", error);
      Swal.fire('Erro!', 'Falha ao atualizar bibliotecario.', 'error');
    });
  }
}

// Remover bibliotecario
function remover(id, botao) {
  Swal.fire({
    icon: 'question',
    title: 'Você tem certeza?',
    showCancelButton: true,
    confirmButtonText: 'Sim',
    cancelButtonText: 'Não'
  }).then((result) => {
    if (result.isConfirmed) {
      fetch(`http://localhost:8080/api/bibliotecario/${id}`, {
        method: "DELETE",
      })
      .then(() => {
        const linha = botao.closest("tr");
        linha.remove();
        Swal.fire("Removido!", "Bibliotecario excluído com sucesso.", "success");
      })
      .catch((error) => {
        console.error("Erro ao remover bibliotecario: ", error);
        Swal.fire("Erro", "Falha ao remover bibliotecario.", "error");
      });
    }
  });
}

// Editar bibliotecario
function editar(botao, id) {
  const linha = botao.closest("tr");
  const nome = linha.querySelector(".nome").textContent;
  const email = linha.querySelector(".email").textContent;

  document.getElementById("nome").value = nome;
  document.getElementById("email").value = email;
  bibliotecarioEditandoId = id;

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
  bibliotecarioEditandoId = null;
  document.querySelector("button[type='submit']").textContent = "Adicionar";
  document.getElementById("cancelarEdicao").classList.add("hidden");
}