package com.example.controle_estoque.demo.controller;

import com.example.controle_estoque.demo.model.*;
import com.example.controle_estoque.demo.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController // Diz que essa classe é um controller REST
@RequestMapping("/api/saida") // URL base para esse controller (ex.: localhost:8080/saida)

public class SaidaController {
     @Autowired // Injeta automaticamente uma instância de ProdutoService
    private SaidaService service;

    // Endpoint para listar todas as saidas
    @GetMapping
    public List<Saida> listar() {
        return service.listar();
    }

    // Endpoint para cadastrar uma nova saida
    @PostMapping
    public Saida cadastrar(@RequestBody Saida saida) {
        return service.salvar(saida);
    }

    // Endpoint para buscar uma saida pelo ID
    @GetMapping("/{id_saida}")
    public ResponseEntity<Saida> buscarPorId(@PathVariable Integer id_saida) {
       return service.buscarPorId(id_saida)
                .map(ResponseEntity::ok) // Se encontrar, retorna 200 OK
                .orElse(ResponseEntity.notFound().build()); // Se não encontrar, retorna 404
    }

    // Endpoint para deletar uma saida pelo ID
    @DeleteMapping("/{id_saida}")
    public void deletar(@PathVariable Integer id_saida) {
        service.deletar(id_saida);
    }
    
}
