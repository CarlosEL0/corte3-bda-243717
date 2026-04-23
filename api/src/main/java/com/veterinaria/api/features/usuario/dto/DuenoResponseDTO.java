package com.veterinaria.api.features.usuario.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DuenoResponseDTO {
    private Integer id;
    private String nombre;
    private String email;
    private String telefono;
}