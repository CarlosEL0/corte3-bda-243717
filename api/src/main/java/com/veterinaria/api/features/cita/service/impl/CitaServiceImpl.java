package com.veterinaria.api.features.cita.service.impl;

import com.veterinaria.api.features.cita.dto.CitaRequestDTO;
import com.veterinaria.api.features.cita.repository.CitaRepository;
import com.veterinaria.api.features.cita.service.CitaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CitaServiceImpl implements CitaService {

    private final CitaRepository citaRepository;

    @Override
    @Transactional
    public Integer agendarCita(CitaRequestDTO request) {
        // Delega la lógica de inserción segura al Stored Procedure de la base de datos
        return citaRepository.spAgendarCita(
                request.getMascotaId(),
                request.getVeterinarioId(),
                request.getFechaHora(),
                request.getMotivo()
        );
    }
}