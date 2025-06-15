//ViaCep

'use strict'; // Modo estrito para evitar erros comuns em JavaScript

// Função que limpa os campos de endereço no formulário
const limparFormulario = () => {
  document.getElementById('Endereço').value = ""; // Limpa campo Endereço
  document.getElementById('Bairro').value = "";   // Limpa campo Bairro
  document.getElementById('Cidade').value = "";   // Limpa campo Cidade
  document.getElementById('Estado').value = "";   // Limpa campo Estado
};

// Função que preenche os campos com os dados retornados da API ViaCEP
const preencherFormulario = (endereco) => {
  document.getElementById('Endereço').value = endereco.logradouro; // Preenche logradouro
  document.getElementById('Bairro').value = endereco.bairro;       // Preenche bairro
  document.getElementById('Cidade').value = endereco.localidade;   // Preenche cidade
  document.getElementById('Estado').value = endereco.uf;           // Preenche estado
};

// Função que verifica se o valor contém apenas números
const eNumero = (numero) => /^[0-9]+$/.test(numero);

// Função que valida se o CEP tem exatamente 8 dígitos numéricos
const cepValido = (cep) => cep.length === 8 && eNumero(cep);

// Função principal que busca os dados do CEP na API ViaCEP
const pesquisarCep = async () => {
  limparFormulario(); // Limpa campos antes de buscar novo CEP

  // Obtém o valor do input, removendo tudo que não for número
  const cep = document.getElementById('cep').value.replace(/\D/g, '');

  // Valida o CEP
  if (!cepValido(cep)) {
    // Alerta se o CEP for inválido
    Swal.fire({
      icon: 'error',
      title: 'CEP inválido',
      text: 'Por favor, digite um CEP válido com 8 números.'
    });
    return; // Encerra a função se o CEP não for válido
  }

  const url = `https://viacep.com.br/ws/${cep}/json/`; // Monta URL da API

  try {
    const dados = await fetch(url); // Realiza a requisição à API
    const endereco = await dados.json(); // Converte resposta em JSON

    // Verifica se a API retornou erro (CEP inexistente)
    if (endereco.hasOwnProperty('erro')) {
      Swal.fire({
        icon: 'warning',
        title: 'CEP não encontrado',
        text: 'Verifique se o CEP está correto.'
      });
    } else {
      preencherFormulario(endereco); // Preenche os campos do formulário
    }
  } catch (error) {
    // Alerta em caso de falha na requisição
    Swal.fire({
      icon: 'error',
      title: 'Erro na requisição',
      text: 'Não foi possível buscar o endereço. Tente novamente mais tarde.'
    });
  }
};

// Adiciona um ouvinte de evento ao campo CEP, que chama a função ao perder o foco
document.addEventListener('DOMContentLoaded', () => {
  document.getElementById('cep')
          .addEventListener('focusout', pesquisarCep);
});



//Cadastro
document.addEventListener("DOMContentLoaded", () => {
  const form = document.getElementById("formCadastro");

  form.addEventListener("submit", async (event) => {
    event.preventDefault(); // Impede o comportamento padrão do formulário

    // Coleta os dados do formulário
    const funcionario = {
  nome: document.getElementById("nome").value,
  email: document.getElementById("email").value,
  senha: document.getElementById("senha").value,
  cep: document.getElementById("cep").value,
  endereco: document.getElementById("Endereço").value,
  numero: document.getElementById("Número").value,
  bairro: document.getElementById("Bairro").value,
  cidade: document.getElementById("Cidade").value,
  estado: document.getElementById("Estado").value
    };

    try {
      const resposta = await fetch("http://localhost:8080/api/funcionarios", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(funcionario)
      });

      if (resposta.ok) {
        Swal.fire({
          icon: "success",
          title: "Funcionário cadastrado!",
          text: "O cadastro foi realizado com sucesso."
        });

        form.reset(); // Limpa o formulário após sucesso
      } else {
        const erro = await resposta.json();
        Swal.fire({
          icon: "error",
          title: "Erro ao cadastrar",
          text: erro.message || "Não foi possível cadastrar o funcionário."
        });
      }

    } catch (error) {
      Swal.fire({
        icon: "error",
        title: "Erro na conexão",
        text: "Não foi possível conectar à API. Verifique o servidor."
      });
    }
  });
});
