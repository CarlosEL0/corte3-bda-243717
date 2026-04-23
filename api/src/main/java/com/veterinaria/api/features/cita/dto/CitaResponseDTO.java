package com.veterinaria.api.features.cita.dto;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class CitaResponseDTO {
    private Integer id;
    private String nombreMascota;
    private String nombreVeterinario;
    private LocalDateTime fechaHora;
    private String motivo;
    private BigDecimal costo;
    private String estado;
}