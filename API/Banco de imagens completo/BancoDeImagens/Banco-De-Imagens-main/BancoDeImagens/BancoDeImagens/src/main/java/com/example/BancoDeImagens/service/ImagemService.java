package com.example.BancoDeImagens.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.example.BancoDeImagens.model.ImagemModel;
import com.example.BancoDeImagens.repository.ImagemRepository;

@Service
public class ImagemService {

    @Autowired
    private ImagemRepository imagemRepository;

    public ImagemModel salvarImagem(ImagemModel imagem) {
        return imagemRepository.save(imagem);
    }

    public List<ImagemModel> listarImagem() {
        return imagemRepository.findAll(); 
    }

    public Optional<ImagemModel> buscarImagemPorId(Long id) {
        return imagemRepository.findById(id);
    }

    public void deletarImagem(Long id) {
        imagemRepository.deleteById(id);
    }

    public ImagemModel atualizarImagem(Long id, ImagemModel novaImagem) {
        return imagemRepository.findById(id).map(imagem -> {
            imagem.setNome(novaImagem.getNome());
            imagem.setUrl(novaImagem.getUrl());
            return imagemRepository.save(imagem);
        }).orElseThrow(() -> new RuntimeException("Imagem não encontrada com ID: " + id));
    }
}
