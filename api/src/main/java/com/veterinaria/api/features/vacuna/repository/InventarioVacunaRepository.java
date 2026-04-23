package com.veterinaria.api.features.vacuna.repository;

import com.veterinaria.api.features.vacuna.entity.InventarioVacuna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventarioVacunaRepository extends JpaRepository<InventarioVacuna, Integer> {
}