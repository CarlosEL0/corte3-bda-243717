package com.veterinaria.api.features.usuario.repository;

import com.veterinaria.api.features.usuario.entity.Veterinario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface VeterinarioRepository extends JpaRepository<Veterinario, Integer> {
    Optional<Veterinario> findByCedula(String cedula);
}