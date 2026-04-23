package com.veterinaria.api.features.cita.service;

import com.veterinaria.api.features.cita.dto.CitaRequestDTO;

public interface CitaService {
    Integer agendarCita(CitaRequestDTO request);
}