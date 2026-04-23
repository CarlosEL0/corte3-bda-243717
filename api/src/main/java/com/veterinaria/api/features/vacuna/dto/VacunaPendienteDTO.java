package com.veterinaria.api.features.vacuna.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacunaPendienteDTO implements Serializable {
    private Integer mascotaId;
    private String nombreMascota;
    private String especie;
    private String nombreDueno;
    private String telefonoDueno;
    private Integer vacunaId;
    private String vacunaPendiente;
}