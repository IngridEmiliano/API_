package com.example.cadastro_alunos.cadastro_alunos.controller;

import com.example.cadastro_alunos.cadastro_alunos.model.Aluno;
import com.example.cadastro_alunos.model.Aluno;
import com.example.cadastro_alunos.service.AlunoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")

public class AlunoController {

    @Autowired
    private AlunoService service;

    @GetMapping
    public List<Aluno> listar() {
        return service.listar();
    
    }

    @PostMapping
    public Aluno cadastrar(@RequestBody Aluno aluno) {
        return service.salvar(aluno);

    }

    @GetMapping("/{id_aluno}")
     public ResponseEntity<Aluno> buscarPorId(@PathVariable Long id_aluno) {
       return service.buscarPorId(id_aluno)
                .map(ResponseEntity::ok) // Se encontrar, retorna 200 OK
                .orElse(ResponseEntity.notFound().build()); // Se não encontrar, retorna 404
    }

    // Endpoint para deletar um produto pelo ID
    @DeleteMapping("/{id_aluno}")
    public void deletar(@PathVariable Long id_aluno) {
        service.deletar(id_aluno);
    
}
}

