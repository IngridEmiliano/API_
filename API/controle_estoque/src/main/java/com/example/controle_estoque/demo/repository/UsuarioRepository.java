package com.example.controle_estoque.demo.repository;

import com.example.controle_estoque.demo.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;  // <-- Import obrigatório!

// Essa camada cuida de acessar os dados no banco.
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {}

/*JpaRepository é uma interface do Spring que já tem métodos prontos:
findAll() → lista todos
save() → salva ou atualiza
deleteById() → deleta
findById() → busca por ID */
