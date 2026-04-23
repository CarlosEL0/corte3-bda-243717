package com.veterinaria.api.features.usuario.service.impl;

import com.veterinaria.api.exception.ResourceNotFoundException;
import com.veterinaria.api.features.usuario.dto.DuenoRequestDTO;
import com.veterinaria.api.features.usuario.dto.DuenoResponseDTO;
import com.veterinaria.api.features.usuario.dto.VeterinarioRequestDTO;
import com.veterinaria.api.features.usuario.dto.VeterinarioResponseDTO;
import com.veterinaria.api.features.usuario.entity.Dueno;
import com.veterinaria.api.features.usuario.entity.Veterinario;
import com.veterinaria.api.features.usuario.mapper.DuenoMapper;
import com.veterinaria.api.features.usuario.mapper.VeterinarioMapper;
import com.veterinaria.api.features.usuario.repository.DuenoRepository;
import com.veterinaria.api.features.usuario.repository.VeterinarioRepository;
import com.veterinaria.api.features.usuario.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final DuenoRepository duenoRepository;
    private final VeterinarioRepository veterinarioRepository;
    private final DuenoMapper duenoMapper;
    private final VeterinarioMapper veterinarioMapper;

    @Override
    @Transactional
    public DuenoResponseDTO guardarDueno(DuenoRequestDTO request) {
        Dueno dueno = duenoMapper.toEntity(request);
        return duenoMapper.toDTO(duenoRepository.save(dueno));
    }

    @Override
    @Transactional(readOnly = true)
    public DuenoResponseDTO obtenerDuenoPorId(Integer id) {
        return duenoRepository.findById(id)
                .map(duenoMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Dueño no encontrado con ID: " + id));
    }

    @Override
    @Transactional
    public VeterinarioResponseDTO guardarVeterinario(VeterinarioRequestDTO request) {
        Veterinario veterinario = veterinarioMapper.toEntity(request);
        return veterinarioMapper.toDTO(veterinarioRepository.save(veterinario));
    }

    @Override
    @Transactional(readOnly = true)
    public VeterinarioResponseDTO obtenerVeterinarioPorId(Integer id) {
        return veterinarioRepository.findById(id)
                .map(veterinarioMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Veterinario no encontrado con ID: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public VeterinarioResponseDTO obtenerVeterinarioPorCedula(String cedula) {
        return veterinarioRepository.findByCedula(cedula)
                .map(veterinarioMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Veterinario no encontrado con cédula: " + cedula));
    }
}