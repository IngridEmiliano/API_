package com.example.controle_estoque.demo.repository;

import com.example.controle_estoque.demo.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {}
