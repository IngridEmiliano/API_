package com.example.CatalogoDeLivro.CatalogoDeLivro.controller;

import com.example.CatalogoDeLivro.CatalogoDeLivro.model.LivroModel;
import com.example.CatalogoDeLivro.CatalogoDeLivro.service.LivroService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")  //permitir que a aplicação frontend acesse a API
@RestController
@RequestMapping("/api/livro")

public class LivroController {

    @Autowired
    private LivroService service;

    @GetMapping
    public List<LivroModel> listar() {
        return service.listar();
    
    }

    @PostMapping
    public LivroModel cadastrar(@RequestBody LivroModel livro) {
        return service.salvar(livro);

    }



    @GetMapping("/{id}")
     public ResponseEntity<LivroModel> buscarPorId(@PathVariable Long id) {
       return service.buscarPorId(id)
                .map(ResponseEntity::ok) // Se encontrar, retorna 200 OK
                .orElse(ResponseEntity.notFound().build()); // Se não encontrar, retorna 404
    }

 
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }

   @PutMapping("/{id}")
public ResponseEntity<LivroModel> atualizar(@PathVariable Long id, @RequestBody LivroModel livroAtualizado) {
    return service.buscarPorId(id)  // procura no banco
            .map(livroExistente -> {
                livroExistente.setTitulo(livroAtualizado.getTitulo());
                livroExistente.setAutor(livroAtualizado.getAutor());
                livroExistente.setGenero(livroAtualizado.getGenero());
                livroExistente.setStatus(livroAtualizado.getStatus());
                livroExistente.setDataDeCadastro(livroAtualizado.getDataDeCadastro());
                LivroModel livroSalvo = service.salvar(livroExistente);   // salva no banco
                return ResponseEntity.ok(livroSalvo);                // retorna OK com o livro atualizado
            })
            .orElse(ResponseEntity.notFound().build()); // se não achar retorna 404
}

}