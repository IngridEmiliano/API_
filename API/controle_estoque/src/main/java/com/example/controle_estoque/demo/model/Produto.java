package com.example.controle_estoque.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

    // @Entity indica que essa classe será uma entidade (tabela) no banco de dados.
    @Entity
    public class Produto {

    // @Id marca esse campo como a chave primária da tabela.
    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id_produto;


    private String nome;

    private Integer quantidade;

    private String status;

    // Construtor padrão sem argumentos.
    // Necessário para o JPA criar instâncias da entidade automaticamente.
    public Produto() {
    }

    // Construtor completo
    // Facilita criar novos objetos Produto já com seus dados prontos, exceto o id.
    public Produto(String nome, Integer quantidade, String status) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.status = status;
    }

    // GETTER para acessar o valor do id_produto.
    public Integer getId_produto() {
        return id_produto;
    }

    /*  SETTER para modificar o valor do id_produto. Normalmente não é usado para id gerado automaticamente,
     mas é bom ter para casos especiais. */
    public void setId_produto(Integer id_produto) {
        this.id_produto = id_produto;
    }

    // GETTER para acessar o nome do produto.
    public String getNome() {
        return nome;
    }

    // SETTER para modificar o nome do produto.
    public void setNome(String nome) {
        this.nome = nome;
    }

    // GETTER para acessar a quantidade em estoque.
    public Integer getQuantidade() {
        return quantidade;
    }

    // SETTER para modificar a quantidade em estoque.
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    // GETTER para acessar o status do produto.
    public String getStatus() {
        return status;
    }

    // SETTER para modificar o status do produto.
    public void setStatus(String status) {
        this.status = status;
    }
}
