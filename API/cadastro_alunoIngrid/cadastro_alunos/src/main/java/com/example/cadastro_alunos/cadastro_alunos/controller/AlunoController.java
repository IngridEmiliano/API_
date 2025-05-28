package com.example.cadastro_alunos.cadastro_alunos.controller;

import com.example.cadastro_alunos.cadastro_alunos.model.AlunoModel;
import com.example.cadastro_alunos.cadastro_alunos.service.AlunoService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aluno")

public class AlunoController {

    @Autowired
    private AlunoService service;

    @GetMapping
    public List<AlunoModel> listar() {
        return service.listar();
    
    }

    @PostMapping
    public AlunoModel cadastrar(@RequestBody AlunoModel aluno) {
        return service.salvar(aluno);

    }

    @GetMapping("/{id_aluno}")
     public ResponseEntity<AlunoModel> buscarPorId(@PathVariable Long id_aluno) {
       return service.buscarPorId(id_aluno)
                .map(ResponseEntity::ok) // Se encontrar, retorna 200 OK
                .orElse(ResponseEntity.notFound().build()); // Se não encontrar, retorna 404
    }

 
    @DeleteMapping("/{id_aluno}")
    public void deletar(@PathVariable Long id_aluno) {
        service.deletar(id_aluno);
    }

    @PutMapping("/{id_aluno}")
    public ResponseEntity<AlunoModel> atualizar(@PathVariable Long id_aluno, @RequestBody AlunoModel alunoAtualizado) {
        return service.buscarPorId(id_aluno)  // procura o aluno no banco
                .map(alunoExistente -> {
                    alunoExistente.setNome(alunoAtualizado.getNome());   // atualiza nome
                    alunoExistente.setEmail(alunoAtualizado.getEmail()); // atualiza email
                    AlunoModel alunoSalvo = service.salvar(alunoExistente);   // salva no banco
                    return ResponseEntity.ok(alunoSalvo);                // retorna OK com o aluno atualizado
                })
                .orElse(ResponseEntity.notFound().build()); // se não achar o aluno, retorna 404
    }
}
