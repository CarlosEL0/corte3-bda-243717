// api/src/main/java/com/veterinaria/api/features/vacuna/service/VacunaService.java
package com.veterinaria.api.features.vacuna.service;

import com.veterinaria.api.features.vacuna.dto.VacunaAplicadaRequestDTO;
import com.veterinaria.api.features.vacuna.dto.VacunaPendienteDTO;
import java.util.List;

public interface VacunaService {
    List<VacunaPendienteDTO> obtenerVacunacionPendiente();
    void aplicarVacuna(VacunaAplicadaRequestDTO request);
}