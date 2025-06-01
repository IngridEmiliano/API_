package com.example.controle_estoque.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.controle_estoque.demo.model.Produto;
import com.example.controle_estoque.demo.repository.ProdutoRepository;

@Service // Diz que é uma classe de serviço
public class ProdutoService {
    
 @Autowired // Injeta automaticamente uma instância do ProdutoRepository
    private ProdutoRepository repository;

    // Método para listar todos os produtos
    public List<Produto> listar() {
        return repository.findAll();
    }

    // Método para salvar (inserir ou atualizar) um produto
    public Produto salvar(Produto produto) {
        return repository.save(produto);
    }

    public Optional<Produto> buscarPorId(Integer id) {
    return repository.findById(id);
}

public void deletar(Integer id) {
    repository.deleteById(id);
}
    }
