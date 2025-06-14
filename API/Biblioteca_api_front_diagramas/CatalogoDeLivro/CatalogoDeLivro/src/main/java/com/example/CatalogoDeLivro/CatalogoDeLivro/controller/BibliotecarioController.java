package com.example.CatalogoDeLivro.CatalogoDeLivro.controller;

import com.example.CatalogoDeLivro.CatalogoDeLivro.model.BibliotecarioModel;
import com.example.CatalogoDeLivro.CatalogoDeLivro.service.BibliotecarioService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*") //permitir que a aplicação frontend acesse a API
@RestController
@RequestMapping("/api/bibliotecario")

public class BibliotecarioController {

    @Autowired
    private BibliotecarioService service;

    @GetMapping
    public List<BibliotecarioModel> listar() {
        return service.listar();
    
    }

    @PostMapping
    public BibliotecarioModel cadastrar(@RequestBody BibliotecarioModel bibliotecario) {
        return service.salvar(bibliotecario);

    }

    @GetMapping("/{id}")
     public ResponseEntity<BibliotecarioModel> buscarPorId(@PathVariable Long id) {
       return service.buscarPorId(id)
                .map(ResponseEntity::ok) // Se encontrar, retorna 200 OK
                .orElse(ResponseEntity.notFound().build()); // Se não encontrar, retorna 404
    }

 
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BibliotecarioModel> atualizar(@PathVariable Long id, @RequestBody BibliotecarioModel bibliotecarioAtualizado) {
        return service.buscarPorId(id)  // procura no banco
                .map(bibliotecarioExistente -> {
                    bibliotecarioExistente.setNome(bibliotecarioAtualizado.getNome());   // atualiza nome
                    bibliotecarioExistente.setEmail(bibliotecarioAtualizado.getEmail()); // atualiza email
                    BibliotecarioModel bibliotecarioSalvo = service.salvar(bibliotecarioExistente);   // salva no banco
                    return ResponseEntity.ok(bibliotecarioSalvo);                // retorna OK com o aluno atualizado
                })
                .orElse(ResponseEntity.notFound().build()); // se não achar retorna 404
    
            }
}