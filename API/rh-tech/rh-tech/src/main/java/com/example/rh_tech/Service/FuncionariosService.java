
package com.example.rh_tech.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.rh_tech.Model.FuncionariosModel;
import com.example.rh_tech.Repository.FuncionariosRepository;

@Service
public class FuncionariosService {

    
 @Autowired // injeta o repositório no serviço
    private FuncionariosRepository repository;  //encapsulando uma variavel

    public List<FuncionariosModel> listar() {
        return repository.findAll(); //findAll busca todos alunos 
    }

    // salva os alunos
    public FuncionariosModel salvar(FuncionariosModel funcionarios) {
        return repository.save(funcionarios); //save salva
    }

    // busca aluno por id
    public Optional<FuncionariosModel> buscarPorId(Long id) {
        return repository.findById(id); //findById busca aluno por id
    }

    // atualizar os dados de um cargo por ID
    public Optional<FuncionariosModel> atualizar(Long id, FuncionariosModel novoFuncionarios) {
    return repository.findById(id).map(funcionario -> {
        funcionario.setNome(novoFuncionarios.getNome());
        funcionario.setEmail(novoFuncionarios.getEmail());
        funcionario.setSenha(novoFuncionarios.getSenha());
        funcionario.setCep(novoFuncionarios.getCep());
        funcionario.setEndereco(novoFuncionarios.getEndereco());
        funcionario.setNumero(novoFuncionarios.getNumero());
        funcionario.setBairro(novoFuncionarios.getBairro());
        funcionario.setCidade(novoFuncionarios.getCidade());
        funcionario.setEstado(novoFuncionarios.getEstado());
        return repository.save(funcionario);
    });
}

    // deleta aluno por id
    public boolean deletar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id); //deleteById deleta aluno por id
            return true;
        } else {
            return false;
        }
    }
}
