package com.example.controle_estoque.demo.controller;

import com.example.controle_estoque.demo.model.*;
import com.example.controle_estoque.demo.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController // Diz que essa classe é um controller REST
@RequestMapping("/produtos") // URL base para esse controller (ex.: localhost:8080/produtos)

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

    // Endpoint para buscar um produto pelo ID
    @GetMapping("/{id_produto}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id_produto) {
       return service.buscarPorId(id_produto)
                .map(ResponseEntity::ok) // Se encontrar, retorna 200 OK
                .orElse(ResponseEntity.notFound().build()); // Se não encontrar, retorna 404
    }

    // Endpoint para deletar um produto pelo ID
    @DeleteMapping("/{id_produto}")
    public void deletar(@PathVariable Long id_produto) {
        service.deletar(id_produto);
    }
}
