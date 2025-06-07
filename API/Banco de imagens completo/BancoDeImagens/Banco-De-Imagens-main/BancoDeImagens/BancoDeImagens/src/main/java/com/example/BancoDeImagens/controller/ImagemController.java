package com.example.BancoDeImagens.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.BancoDeImagens.model.ImagemModel;
import com.example.BancoDeImagens.service.ImagemService;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.http.MediaType;
import java.nio.file.Path;

@RestController
@RequestMapping("/api/imagem")
@CrossOrigin(origins = "*")
public class ImagemController {

    @Autowired
    private ImagemService imagemService;

    // POST: adiciona imagem
    @PostMapping
    public ImagemModel adicionarImagem(@RequestBody ImagemModel imagem) {
        return imagemService.salvarImagem(imagem);
    }

    // GET: lista todas as imagens
    @GetMapping
    public List<ImagemModel> listarImagem() {
        return imagemService.listarImagem();
    }

    // DELETE: remove imagem por id
    @DeleteMapping("/{id}")
    public void deletarImagem(@PathVariable Long id) {
        imagemService.deletarImagem(id);
    }

    // PUT: atualiza imagem pelo id
    @PutMapping("/{id}")
    public ImagemModel atualizaImagem(@PathVariable Long id, @RequestBody ImagemModel imagem) {
        return imagemService.atualizarImagem(id, imagem);
    }

    @GetMapping("/imagem/{nomeArquivo:.+}")
public ResponseEntity<Resource> getImagem(@PathVariable String nomeArquivo) {
    try {
        Path caminho = Paths.get("uploads").resolve(nomeArquivo).normalize();
        Resource recurso = new UrlResource(caminho.toUri());

        if (recurso.exists() && recurso.isReadable()) {
            String contentType = Files.probeContentType(caminho);
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType != null ? contentType : "application/octet-stream"))
                    .body(recurso);
        } else {
            return ResponseEntity.notFound().build();
        }
    } catch (Exception e) {
        return ResponseEntity.internalServerError().build();
    }
}
}
