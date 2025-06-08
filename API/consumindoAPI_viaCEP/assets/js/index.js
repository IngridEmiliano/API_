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
