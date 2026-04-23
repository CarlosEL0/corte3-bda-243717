package com.veterinaria.api.features.mascota.service;

import com.veterinaria.api.features.mascota.dto.MascotaRequestDTO;
import com.veterinaria.api.features.mascota.dto.MascotaResponseDTO;
import java.util.List;

public interface MascotaService {
    MascotaResponseDTO crearMascota(MascotaRequestDTO request);
    List<MascotaResponseDTO> buscarPorNombre(String nombre);
}