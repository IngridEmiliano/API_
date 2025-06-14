package com.example.CatalogoDeLivro.CatalogoDeLivro.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CatalogoDeLivro.CatalogoDeLivro.model.BibliotecarioModel;
import com.example.CatalogoDeLivro.CatalogoDeLivro.model.LivroModel;
import com.example.CatalogoDeLivro.CatalogoDeLivro.repository.LivroRepository;



@Service
public class LivroService {
    
    @Autowired // injeta o repositório no serviço
    private LivroRepository repository;  //encapsulando uma variavel

    @Autowired
private BibliotecarioService bibliotecarioService;

   

    public List<LivroModel> listar() {
        return repository.findAll(); //findAll busca todos 
    }

    // busca por id
    public Optional<LivroModel> buscarPorId(Long id) {
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

    //salvar
   public LivroModel salvar(LivroModel livro) {
    if (livro.getBibliotecario() == null || livro.getBibliotecario().getId() == null) {
        throw new RuntimeException("Bibliotecário deve ser informado");
    }

    // Buscar o bibliotecário no banco e garantir que existe
    BibliotecarioModel bib = bibliotecarioService.buscarPorId(livro.getBibliotecario().getId())
        .orElseThrow(() -> new RuntimeException("Bibliotecário não encontrado"));

    livro.setBibliotecario(bib);

    // Garantir dataDeCadastro atual
    if (livro.getDataDeCadastro() == null) {
        livro.setDataDeCadastro(LocalDateTime.now());  // ou LocalDateTime.now(), conforme o tipo do campo
    }

    return repository.save(livro);

}

}