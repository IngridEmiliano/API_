package com.example.controle_estoque.demo.controller;

import com.example.controle_estoque.demo.model.*;
import com.example.controle_estoque.demo.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController // Diz que essa classe é um controller REST
@RequestMapping("api/usuarios")


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

    @GetMapping("/{id_usuario}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id_usuario) {
        return service.buscarPorId(id_usuario)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id_usuario}")
    public ResponseEntity<Void> deletar(@PathVariable Long id_usuario) {
        service.deletar(id_usuario);
        return ResponseEntity.noContent().build();
    }
}
