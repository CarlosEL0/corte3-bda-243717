package com.veterinaria.api.features.mascota.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class MascotaResponseDTO {
    private Integer id;
    private String nombre;
    private String especie;
    private LocalDate fechaNacimiento;
    private Integer duenoId;
    private String nombreDueno;
}