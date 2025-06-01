package com.example.controle_estoque.demo.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

// A anotação @Entity indica que essa classe será uma entidade (tabela) no banco de dados.
@Entity
public class Saida {

// @Id marca esse campo como a chave primária da tabela.
@Id

// @GeneratedValue indica que o valor desse campo será gerado automaticamente pelo banco, usando a estratégia IDENTITY (auto-incremento).
@GeneratedValue(strategy = GenerationType.IDENTITY)

//dados da tabela
private Integer id_saida;


private LocalDate data;

// Relacionamento com Usuario (chave estrangeira)
@ManyToOne
@JoinColumn(name = "id_usuario", nullable = false)
private Usuario usuario;

// Relacionamento com Produto (chave estrangeira)
@ManyToOne
@JoinColumn(name = "id_produto", nullable = false)
private Produto produto;

private Integer quantidade_saida;

// Construtor padrão sem argumentos.
// Necessário para o JPA criar instâncias da entidade automaticamente.
public Saida() {
}

// Construtor completo
// Facilita criar novos objetos Produto já com seus dados prontos, exceto o id.
public Saida(LocalDate data, Usuario usuario, Produto produto, Integer quantidade_saida) {
    this.data = data;
    this.usuario = usuario;
    this.produto = produto;
    this.quantidade_saida = quantidade_saida;

}

// GETTER para acessar o valor do id_saida.
    public Integer getId_saida() {
        return id_saida;
    }
    /*  SETTER para modificar o valor do id_saida. Normalmente não é usado para id gerado automaticamente,
     mas é bom ter para casos especiais. */
    public void setId_saida(Integer id_saida) {
        this.id_saida = id_saida;
    }

    // GETTER para acessar a data.
    public LocalDate getdata() {
        return data;
    }

    // SETTER para modificar o nome do usuario.
    public void setData(LocalDate data) {
        this.data = data;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade_saida() {
        return quantidade_saida;
    }

    public void setQuantidade_saida(Integer quantidade_saida) {
        this.quantidade_saida = quantidade_saida;
    }
}
