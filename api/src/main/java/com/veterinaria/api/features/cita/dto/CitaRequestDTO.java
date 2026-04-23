package com.veterinaria.api.features.cita.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CitaRequestDTO {

    @NotNull(message = "El ID del animalito es requerido")
    private Integer mascotaId;

    @NotNull(message = "El ID del veterinario es requerido")
    private Integer veterinarioId;

    @NotNull(message = "La fecha y hora son requeridas")
    private LocalDateTime fechaHora;

    private String motivo;
}