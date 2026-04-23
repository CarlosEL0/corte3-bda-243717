package com.veterinaria.api.features.mascota.repository;

import com.veterinaria.api.features.mascota.entity.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Integer> {
    List<Mascota> findByNombreContainingIgnoreCase(String nombre);
}