package com.example.BancoDeImagens.repository;

import com.example.BancoDeImagens.model.ImagemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagemRepository extends JpaRepository<ImagemModel, Long> {
}
