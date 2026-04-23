package com.veterinaria.api.features.usuario.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class VeterinarioRequestDTO {

    @NotBlank(message = "El nombre del veterinario es requerido")
    private String nombre;

    @NotBlank(message = "La cédula es requerida")
    private String cedula;

    private String diasDescanso;
    private Boolean activo;
}