// api/src/main/java/com/veterinaria/api/features/mascota/service/impl/MascotaServiceImpl.java
package com.veterinaria.api.features.mascota.service.impl;

import com.veterinaria.api.exception.ResourceNotFoundException;
import com.veterinaria.api.features.mascota.dto.MascotaRequestDTO;
import com.veterinaria.api.features.mascota.dto.MascotaResponseDTO;
import com.veterinaria.api.features.mascota.entity.Mascota;
import com.veterinaria.api.features.mascota.mapper.MascotaMapper;
import com.veterinaria.api.features.mascota.repository.MascotaRepository;
import com.veterinaria.api.features.mascota.service.MascotaService;
import com.veterinaria.api.features.usuario.entity.Dueno;
import com.veterinaria.api.features.usuario.repository.DuenoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MascotaServiceImpl implements MascotaService {

    private final MascotaRepository mascotaRepository;
    private final DuenoRepository duenoRepository;
    private final MascotaMapper mascotaMapper;

    @Override
    @Transactional
    public MascotaResponseDTO crearMascota(MascotaRequestDTO request) {
        Dueno dueno = duenoRepository.findById(request.getDuenoId())
                .orElseThrow(() -> new ResourceNotFoundException("Dueño no encontrado con ID: " + request.getDuenoId()));

        Mascota mascota = mascotaMapper.toEntity(request);
        mascota.setDueno(dueno);

        Mascota mascotaGuardada = mascotaRepository.save(mascota);
        return mascotaMapper.toDTO(mascotaGuardada);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MascotaResponseDTO> buscarPorNombre(String nombre) {
        return mascotaRepository.findByNombreContainingIgnoreCase(nombre).stream()
                .map(mascotaMapper::toDTO)
                .toList();
    }
}