package com.veterinaria.api.features.vacuna.repository;

import com.veterinaria.api.features.vacuna.dto.VacunaPendienteDTO;
import com.veterinaria.api.features.vacuna.entity.VacunaAplicada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VacunaAplicadaRepository extends JpaRepository<VacunaAplicada, Integer> {

    // Consulta nativa a la vista para el reporte de vacunación pendiente
    // El mapeo se realiza directamente al DTO mediante el constructor
    @Query(value = "SELECT mascota_id, nombre_mascota, especie, nombre_dueno, telefono, vacuna_id, vacuna_pendiente FROM v_mascotas_vacunacion_pendiente", nativeQuery = true)
    List<Object[]> findVacunacionPendienteRaw();
}