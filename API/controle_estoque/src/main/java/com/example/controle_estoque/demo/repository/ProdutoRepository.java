package com.example.controle_estoque.demo.repository;

import com.example.controle_estoque.demo.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;  // <-- Import obrigatório!

// Essa camada cuida de acessar os dados no banco.
public interface ProdutoRepository extends JpaRepository<Produto, Long> {}

/*JpaRepository é uma interface do Spring que já tem métodos prontos:
findAll() → lista todos
save() → salva ou atualiza
deleteById() → deleta
findById() → busca por ID */
