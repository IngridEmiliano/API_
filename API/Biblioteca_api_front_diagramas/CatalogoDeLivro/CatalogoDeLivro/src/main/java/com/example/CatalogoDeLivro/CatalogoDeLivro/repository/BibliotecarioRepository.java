package com.example.CatalogoDeLivro.CatalogoDeLivro.repository;

import com.example.CatalogoDeLivro.CatalogoDeLivro.model.BibliotecarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BibliotecarioRepository extends JpaRepository<BibliotecarioModel, Long> {

}