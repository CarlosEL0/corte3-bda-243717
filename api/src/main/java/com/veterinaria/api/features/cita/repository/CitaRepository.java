package com.veterinaria.api.features.cita.repository;

import com.veterinaria.api.features.cita.entity.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Integer> {

    // Invocación del Stored Procedure definido en el archivo 03_procedures.sql
    @Procedure(procedureName = "sp_agendar_cita")
    Integer spAgendarCita(
            @Param("p_mascota_id") Integer mascotaId,
            @Param("p_veterinario_id") Integer veterinarioId,
            @Param("p_fecha_hora") LocalDateTime fechaHora,
            @Param("p_motivo") String motivo
    );
}