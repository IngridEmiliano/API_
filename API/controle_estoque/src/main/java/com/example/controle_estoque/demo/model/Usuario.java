package com.example.controle_estoque.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// A anotação @Entity indica que essa classe será uma entidade (tabela) no banco de dados.
@Entity
public class Usuario {

// @Id marca esse campo como a chave primária da tabela.
@Id

// @GeneratedValue indica que o valor desse campo será gerado automaticamente pelo banco, usando a estratégia IDENTITY (auto-incremento).
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    //dados da tabela
    private Long id_usuario;
    
    private String nome;

    private String endereco;

    private String setor;

    // Construtor padrão sem argumentos.
    // Necessário para o JPA criar instâncias da entidade automaticamente.
    public Usuario() {
    }
    
    // Construtor completo
    // Facilita criar novos objetos Produto já com seus dados prontos, exceto o id.
    public Usuario(String nome, String endereco, String setor) {
        this.nome = nome;
        this.endereco = endereco;
        this.setor = setor;
    }

    // GETTER para acessar o valor do id_usuario.
    public Long getId_usuario() {
        return id_usuario;
    }
    /*  SETTER para modificar o valor do id_produto. Normalmente não é usado para id gerado automaticamente,
     mas é bom ter para casos especiais. */
    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    // GETTER para acessar o nome do usuario.
    public String getNome() {
        return nome;
    }

    // SETTER para modificar o nome do usuario.
    public void setNome(String nome) {
        this.nome = nome;
    }

    // GETTER para acessar o endereco.
    public String getEndereco() {
        return endereco;
    }

    // SETTER para modificar o endereco.
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    // GETTER para acessar o setor.
    public String getSetor() {
        return setor;
    }

    // SETTER para modificar o setor.
    public void setSetor(String setor) {
        this.setor = setor;
    }
    
}
