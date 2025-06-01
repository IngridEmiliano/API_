package com.example.controle_estoque.demo.controller;

import com.example.controle_estoque.demo.model.*;
import com.example.controle_estoque.demo.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController // Diz que essa classe é um controller REST
@RequestMapping("/api/produtos") // URL base para esse controller (ex.: localhost:8080/produtos)

public class ProdutoController {

    @Autowired // Injeta automaticamente uma instância de ProdutoService
    private ProdutoService service;

    // Endpoint para listar todos os produtos
    @GetMapping
    public List<Produto> listar() {
        return service.listar();
    }

    // Endpoint para cadastrar um novo produto
    @PostMapping
    public Produto cadastrar(@RequestBody Produto produto) {
        return service.salvar(produto);
    }

    @GetMapping("/{id_produto}")
public ResponseEntity<Produto> buscarPorId(@PathVariable Integer id_produto) {
    return service.buscarPorId(id_produto)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
}

@DeleteMapping("/{id_produto}")
public void deletar(@PathVariable Integer id_produto) {
    service.deletar(id_produto);
}
}
