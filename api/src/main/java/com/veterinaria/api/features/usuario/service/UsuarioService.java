package com.veterinaria.api.features.usuario.service;

import com.veterinaria.api.features.usuario.dto.DuenoRequestDTO;
import com.veterinaria.api.features.usuario.dto.DuenoResponseDTO;
import com.veterinaria.api.features.usuario.dto.VeterinarioRequestDTO;
import com.veterinaria.api.features.usuario.dto.VeterinarioResponseDTO;

public interface UsuarioService {
    // Gestión de Dueños
    DuenoResponseDTO guardarDueno(DuenoRequestDTO request);
    DuenoResponseDTO obtenerDuenoPorId(Integer id);

    // Gestión de Veterinarios
    VeterinarioResponseDTO guardarVeterinario(VeterinarioRequestDTO request);
    VeterinarioResponseDTO obtenerVeterinarioPorId(Integer id);
    VeterinarioResponseDTO obtenerVeterinarioPorCedula(String cedula);
}