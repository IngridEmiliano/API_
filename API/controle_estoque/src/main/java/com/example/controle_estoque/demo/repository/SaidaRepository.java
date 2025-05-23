package com.example.controle_estoque.demo.repository;

import com.example.controle_estoque.demo.model.Saida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Essa anotação marca que é um componente de acesso a dados
@Repository
public interface SaidaRepository extends JpaRepository<Saida, Long> {
}