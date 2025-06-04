package com.example.cadastro_alunos.cadastro_alunos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cadastro_alunos.cadastro_alunos.model.AlunoModel;
import com.example.cadastro_alunos.cadastro_alunos.repository.AlunoRepository;

@Service
public class AlunoService {
    
    @Autowired // injeta o repositório no serviço
    private AlunoRepository repository;  //encapsulando uma variavel
   

    //atras os alunos

    public List<AlunoModel> listar() {
        return repository.findAll(); //findAll busca todos alunos 
    }

    // salva os alunos
    public AlunoModel salvar(AlunoModel aluno) {
        return repository.save(aluno); //save salva
    }

    // busca aluno por id
    public Optional<AlunoModel> buscarPorId(Long id_aluno) {
        return repository.findById(id_aluno); //findById busca aluno por id
    }

    // deleta aluno por id
    public boolean deletar(Long id_aluno) {
        if (repository.existsById(id_aluno)) {
            repository.deleteById(id_aluno); //deleteById deleta aluno por id
            return true;
        } else {
            return false;
        }
    }
}
