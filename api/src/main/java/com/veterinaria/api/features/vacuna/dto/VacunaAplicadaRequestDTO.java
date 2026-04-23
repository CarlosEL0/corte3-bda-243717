package com.veterinaria.api.features.vacuna.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class VacunaAplicadaRequestDTO {

    @NotNull(message = "El ID de la mascota es requerido")
    private Integer mascotaId;

    @NotNull(message = "El ID de la vacuna es requerido")
    private Integer vacunaId;

    //  El veterinarioId lo sacaremos del contexto de seguridad,

    @NotNull(message = "El ID del veterinario es requerido")
    private Integer veterinarioId;

    @NotNull(message = "La fecha de aplicación es requerida")
    private LocalDate fechaAplicacion;

    @NotNull(message = "El costo es requerido")
    @Positive(message = "El costo debe ser mayor a 0")
    private BigDecimal costoCobrado;
}