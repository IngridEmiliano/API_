package com.example.controle_estoque.demo.controller;

import com.example.controle_estoque.demo.model.*;
import com.example.controle_estoque.demo.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController // Diz que essa classe é um controller REST
@RequestMapping("/usuarios") // URL base para esse controller (ex.: localhost:8080/produtos)

public class UsuarioController {
    @Autowired // Injeta automaticamente uma instância de UsuarioService
    private UsuarioService service;

    // Endpoint para listar todos os usuarios
    @GetMapping
    public List<Usuario> listar() {
        return service.listar();
    }

    // Endpoint para cadastrar um novo usuario
    @PostMapping
    public Usuario cadastrar(@RequestBody Usuario usuario) {
        return service.salvar(usuario);
    }

    // Endpoint para buscar um usuario pelo ID
    @GetMapping("/{id_usuario}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id_usuario) {
       return service.buscarPorId(id_usuario)
                .map(ResponseEntity::ok) // Se encontrar, retorna 200 OK
                .orElse(ResponseEntity.notFound().build()); // Se não encontrar, retorna 404
    }

    // Endpoint para deletar um usuario pelo ID
    @DeleteMapping("/{id_produto}")
    public void deletar(@PathVariable Long id_usuario) {
        service.deletar(id_usuario);
    }
    
}
