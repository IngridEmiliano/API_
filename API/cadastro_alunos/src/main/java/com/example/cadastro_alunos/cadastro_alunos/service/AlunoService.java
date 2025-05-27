package com.example.cadastro_alunos.cadastro_alunos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cadastro_alunos.cadastro_alunos.model.Aluno;
import com.example.cadastro_alunos.cadastro_alunos.repository.AlunoRepository;

@Service
public class AlunoService {
    
    @Autowired
    private AlunoRepository repository;

    public List<Aluno> listar() {
        return repository.findAll();
    }

    public Aluno salvar(Aluno aluno) {
        return repository.save(aluno);
    }

    public Optional<Aluno> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public boolean deletar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}

