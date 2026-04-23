package com.veterinaria.api.features.usuario.repository;

import com.veterinaria.api.features.usuario.entity.Dueno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DuenoRepository extends JpaRepository<Dueno, Integer> {
}