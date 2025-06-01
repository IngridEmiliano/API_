package com.example.controle_estoque.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.controle_estoque.demo.model.Saida;
import com.example.controle_estoque.demo.repository.SaidaRepository;

@Service // Diz que é uma classe de serviço
public class SaidaService {
    @Autowired // Injeta automaticamente uma instância do ProdutoRepository
    private SaidaRepository repository;

    // Método para listar todas as saidas
    public List<Saida> listar() {
        return repository.findAll();
    }

    // Método para salvar (inserir ou atualizar) uma saida
    public Saida salvar(Saida saida) {
        return repository.save(saida);
    }

   public Optional<Saida> buscarPorId(Integer id) {
    return repository.findById(id);
}

public void deletar(Integer id) {
    repository.deleteById(id);

    }   
}
