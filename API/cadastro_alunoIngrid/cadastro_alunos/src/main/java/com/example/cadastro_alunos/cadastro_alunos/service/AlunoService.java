package com.example.cadastro_alunos.cadastro_alunos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cadastro_alunos.cadastro_alunos.model.AlunoModel;
import com.example.cadastro_alunos.cadastro_alunos.repository.AlunoRepository;

@Service
public class AlunoService {
    
    @Autowired
    private AlunoRepository repository;

    public List<AlunoModel> listar() {
        return repository.findAll();
    }

    public AlunoModel salvar(AlunoModel aluno) {
        return repository.save(aluno);
    }

    public Optional<AlunoModel> buscarPorId(Long id_aluno) {
        return repository.findById(id_aluno);
    }

    public boolean deletar(Long id_aluno) {
        if (repository.existsById(id_aluno)) {
            repository.deleteById(id_aluno);
            return true;
        } else {
            return false;
        }
    }
}
