package com.example.rh_tech.Repository;

import com.example.rh_tech.Model.FuncionariosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionariosRepository extends JpaRepository<FuncionariosModel, Long> {
} 