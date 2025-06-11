package com.example.rh_tech.Repository;

import com.example.rh_tech.Model.CargosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargosRepository extends JpaRepository<CargosModel, Long> {
} 