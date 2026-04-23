package com.veterinaria.api.features.usuario.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VeterinarioResponseDTO {
    private Integer id;
    private String nombre;
    private String cedula;
    private String diasDescanso;
    private Boolean activo;
}