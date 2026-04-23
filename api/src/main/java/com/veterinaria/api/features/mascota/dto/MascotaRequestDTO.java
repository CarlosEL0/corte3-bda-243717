package com.veterinaria.api.features.mascota.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
public class MascotaRequestDTO {

    @NotBlank(message = "El nombre del animalito es requerido")
    private String nombre;

    @NotBlank(message = "La especie del animalito es requerida")
    private String especie;

    @NotNull(message = "La fecha de nacimiento es requerida")
    private LocalDate fechaNacimiento;

    @NotNull(message = "El ID del dueño es requerido")
    private Integer duenoId;
}