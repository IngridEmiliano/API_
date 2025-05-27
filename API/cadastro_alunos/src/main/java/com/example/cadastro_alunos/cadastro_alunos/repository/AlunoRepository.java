package com.example.cadastro_alunos.cadastro_alunos.repository;

import com.example.cadastro_alunos.cadastro_alunos.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

}
