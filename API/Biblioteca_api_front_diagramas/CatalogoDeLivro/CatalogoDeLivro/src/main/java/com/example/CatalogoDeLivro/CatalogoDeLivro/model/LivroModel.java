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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.JoinColumn;
import java.time.LocalDateTime;


@Entity // entidade no banco de dados
@Table(name ="Livro")
//chama getters e setters automaticamente
@Setter
@Getter
@NoArgsConstructor
public class LivroModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne (optional = false)
    @JoinColumn(name = "id_bibliotecario") 
    private BibliotecarioModel bibliotecario;

    @Column (nullable = false)
    private String titulo;

    @Column (nullable = false)
    private String autor;

    @Column (nullable = false)
    private String genero;

    @Column(name = "Status", nullable = false)
    private String status;

    @Column(name = "DataDeCadastro", nullable = false)
    private LocalDateTime dataDeCadastro;

    @PrePersist
    public void prePersist() {
        if (dataDeCadastro == null) {
            dataDeCadastro = LocalDateTime.now();
        }
    }

}


