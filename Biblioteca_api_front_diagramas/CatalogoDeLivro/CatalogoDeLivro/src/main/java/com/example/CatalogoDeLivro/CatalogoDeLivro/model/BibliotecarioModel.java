package com.example.CatalogoDeLivro.CatalogoDeLivro.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // entidade no banco de dados
@Table(name ="Bibliotecario")
//chama getters e setters automaticamente
@Setter
@Getter
@NoArgsConstructor
public class BibliotecarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private String nome;


    @Column (nullable = false)
    private String email;

}