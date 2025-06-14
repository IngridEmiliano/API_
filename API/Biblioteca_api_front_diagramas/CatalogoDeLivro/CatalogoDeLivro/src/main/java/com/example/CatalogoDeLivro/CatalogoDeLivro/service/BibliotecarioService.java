package com.example.CatalogoDeLivro.CatalogoDeLivro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CatalogoDeLivro.CatalogoDeLivro.model.BibliotecarioModel;
import com.example.CatalogoDeLivro.CatalogoDeLivro.repository.BibliotecarioRepository;

@Service
public class BibliotecarioService {
    
    @Autowired // injeta o repositório no serviço
    private BibliotecarioRepository repository;  //encapsulando uma variavel
   

    public List<BibliotecarioModel> listar() {
        return repository.findAll(); //findAll busca todos 
    }

    // salva 
    public BibliotecarioModel salvar(BibliotecarioModel aluno) {
        return repository.save(aluno); //save salva
    }

    // busca por id
    public Optional<BibliotecarioModel> buscarPorId(Long id) {
        return repository.findById(id); //findById busca por id
    }

    // deleta por id
    public boolean deletar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id); //deleteById deleta por id
            return true;
        } else {
            return false;
        }
    }
}