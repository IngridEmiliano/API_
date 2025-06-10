package com.example.rh_tech.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rh_tech.Model.CargosModel;
import com.example.rh_tech.Repository.CargosRepository;

@Service
public class CargosService {

    
 @Autowired // injeta o repositório no serviço
    private CargosRepository repository;  //encapsulando uma variavel

    public List<CargosModel> listar() {
        return repository.findAll(); //findAll busca todos alunos 
    }

    // salva os alunos
    public CargosModel salvar(CargosModel cargos) {
        return repository.save(cargos); //save salva
    }

    // busca aluno por id
    public Optional<CargosModel> buscarPorId(Long id) {
        return repository.findById(id); //findById busca aluno por id
    }

    //
    public Optional<CargosModel> atualizar(Long id, CargosModel novoCargo) {
    return repository.findById(id).map(cargo -> {
        cargo.setNome(novoCargo.getNome());
        cargo.setDescricao(novoCargo.getDescricao());
        return repository.save(cargo);
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
