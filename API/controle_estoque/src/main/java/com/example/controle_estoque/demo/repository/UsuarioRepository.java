package com.example.controle_estoque.demo.repository;

import com.example.controle_estoque.demo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {}
