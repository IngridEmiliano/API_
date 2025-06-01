package com.example.controle_estoque.demo.repository;

import com.example.controle_estoque.demo.model.Saida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaidaRepository extends JpaRepository<Saida, Integer> {}
