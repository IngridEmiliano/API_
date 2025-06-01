package com.example.controle_estoque.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.controle_estoque.demo.model.Usuario;
import com.example.controle_estoque.demo.repository.UsuarioRepository;

@Service // Diz que é uma classe de serviço
public class UsuarioService {

     @Autowired // Injeta automaticamente uma instância do UsuarioRepository
    private UsuarioRepository repository;

        // Método para listar todos os usuarios
    public List<Usuario> listar() {
        return repository.findAll();
    }

    // Método para salvar (inserir ou atualizar) um usuario
    public Usuario salvar(Usuario usuario) {
        return repository.save(usuario);
    }

    // Método para buscar um usuario pelo ID
    public Optional<Usuario> buscarPorId(Long id) {
        return repository.findById(id);
    }

    // Método para deletar um produto pelo ID
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}

