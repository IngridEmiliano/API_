let livroEditandoId = null;

// Executa tudo após carregar o DOM
document.addEventListener("DOMContentLoaded", () => {
  buscarLivro();
  buscarBibliotecarios();

  document.getElementById("formCadastro").addEventListener("submit", cadastrar);
  document.getElementById("cancelarEdicao").addEventListener("click", cancelarEdicao);
});

// Buscar todos os livros
function buscarLivro() {
  fetch("http://localhost:8080/api/livro")
    .then(response => response.json())
    .then(data => atualizarTabela(data))
    .catch(error => {
      console.error("Erro ao buscar livros:", error);
    });
}

// Atualiza a tabela com os livros recebidos
function atualizarTabela(livros) {
  const tabela = document.getElementById('tabelaCorpo');
  tabela.innerHTML = "";

  livros.forEach(livro => {
    const tr = document.createElement("tr");
    tr.innerHTML = `
      <td class="px-4 py-2">${livro.id}</td>
      <td class="px-4 py-2 id_bibliotecario">${livro.bibliotecario ? livro.bibliotecario.id : ''}</td>
      <td class="px-4 py-2 titulo">${livro.titulo}</td>
      <td class="px-4 py-2 autor">${livro.autor}</td>
      <td class="px-4 py-2 genero">${livro.genero}</td>
      <td class="px-4 py-2 Status">${livro.status}</td>
      <td class="px-4 py-2 DataDeCadastro">${livro.dataDeCadastro}</td>
      <td class="px-4 py-2">
        <button class="bg-yellow-500 text-white px-2 py-1 rounded mr-2" onclick="editar(this, ${livro.id})">Editar</button>
        <button class="bg-red-500 text-white px-2 py-1 rounded" onclick="remover(${livro.id}, this)">Remover</button>
      </td>
    `;
    tabela.appendChild(tr);
  });
}

// Cadastrar novo livro ou atualizar existente
function cadastrar(event) {
  event.preventDefault();

  const id_bibliotecario = document.getElementById('bibliotecario').value;
  const titulo = document.getElementById('titulo').value;
  const autor = document.getElementById('autor').value;
  const genero = document.getElementById('genero').value;
  const Status = document.getElementById('Status').value;

  if (!id_bibliotecario || !titulo || !autor || !genero || !Status) {
    Swal.fire({
      icon: 'error',
      title: 'Erro!',
      text: 'Preencha todos os campos para cadastrar.'
    });
    return;
  }

  const livro = {
    bibliotecario: { id: Number(id_bibliotecario) },
    titulo,
    autor,
    genero,
    status: Status
  };

  if (livroEditandoId === null) {
    // Criar novo livro
    fetch('http://localhost:8080/api/livro', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(livro)
    })
    .then(res => {
      if (!res.ok) throw new Error('Erro ao cadastrar livro');
      return res.json();
    })
    .then(() => {
      buscarLivro();
      limparFormulario();
      Swal.fire('Sucesso!', 'Livro cadastrado com sucesso.', 'success');
    })
    .catch(error => {
      console.error("Erro ao cadastrar:", error);
      Swal.fire('Erro!', 'Falha ao cadastrar livro.', 'error');
    });
  } else {
    // Atualizar livro existente
    fetch(`http://localhost:8080/api/livro/${livroEditandoId}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(livro)
    })
    .then(res => {
      if (!res.ok) throw new Error('Erro ao atualizar livro');
      return res.json();
    })
    .then(() => {
      buscarLivro();
      limparFormulario();
      Swal.fire('Sucesso!', 'Livro atualizado com sucesso.', 'success');
    })
    .catch(error => {
      console.error("Erro ao atualizar:", error);
      Swal.fire('Erro!', 'Falha ao atualizar livro.', 'error');
    });
  }
}

// Remover livro
function remover(id, botao) {
  Swal.fire({
    icon: 'question',
    title: 'Você tem certeza que quer remover?',
    showCancelButton: true,
    confirmButtonText: 'Sim',
    cancelButtonText: 'Não'
  }).then(result => {
    if (result.isConfirmed) {
      fetch(`http://localhost:8080/api/livro/${id}`, {
        method: "DELETE",
      })
      .then(res => {
        if (!res.ok) throw new Error('Erro ao remover livro');
        const linha = botao.closest("tr");
        linha.remove();
        Swal.fire("Removido!", "Livro excluído com sucesso.", "success");
      })
      .catch(error => {
        console.error("Erro ao remover livro:", error);
        Swal.fire("Erro", "Falha ao remover livro.", "error");
      });
    }
  });
}

// Preenche o formulário para edição
function editar(botao, id) {
  const linha = botao.closest("tr");
  const id_bibliotecario = linha.querySelector(".id_bibliotecario").textContent;
  const titulo = linha.querySelector(".titulo").textContent;
  const autor = linha.querySelector(".autor").textContent;
  const genero = linha.querySelector(".genero").textContent;
  const Status = linha.querySelector(".Status").textContent;

  document.getElementById("bibliotecario").value = id_bibliotecario;
  document.getElementById("titulo").value = titulo;
  document.getElementById("autor").value = autor;
  document.getElementById("genero").value = genero;
  document.getElementById("Status").value = Status;

  livroEditandoId = id;

  document.querySelector("button[type='submit']").textContent = "Salvar";
  document.getElementById("cancelarEdicao").classList.remove("hidden");
}

// Busca bibliotecários para popular o select
function buscarBibliotecarios() {
  fetch("http://localhost:8080/api/bibliotecario")
    .then(res => res.json())
    .then(data => {
      const select = document.getElementById("bibliotecario");
      select.innerHTML = '<option value="" disabled selected>Selecione o bibliotecário</option>';

      data.forEach(bibliotecario => {
        const option = document.createElement("option");
        option.value = bibliotecario.id;
        option.textContent = bibliotecario.nome;
        select.appendChild(option);
      });
    })
    .catch(err => {
      console.error("Erro ao buscar bibliotecarios:", err);
    });
}

// Cancelar edição e limpar formulário
function cancelarEdicao() {
  limparFormulario();
}

// Limpa formulário e reseta estado
function limparFormulario() {
  document.getElementById("formCadastro").reset();
  livroEditandoId = null;
  document.querySelector("button[type='submit']").textContent = "Adicionar";
  document.getElementById("cancelarEdicao").classList.add("hidden");
}
