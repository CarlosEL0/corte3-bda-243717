// api/src/main/java/com/veterinaria/api/features/vacuna/service/impl/VacunaServiceImpl.java
package com.veterinaria.api.features.vacuna.service.impl;

import com.veterinaria.api.exception.ResourceNotFoundException;
import com.veterinaria.api.features.mascota.entity.Mascota;
import com.veterinaria.api.features.mascota.repository.MascotaRepository;
import com.veterinaria.api.features.usuario.entity.Veterinario;
import com.veterinaria.api.features.usuario.repository.VeterinarioRepository;
import com.veterinaria.api.features.vacuna.dto.VacunaAplicadaRequestDTO;
import com.veterinaria.api.features.vacuna.dto.VacunaPendienteDTO;
import com.veterinaria.api.features.vacuna.entity.InventarioVacuna;
import com.veterinaria.api.features.vacuna.entity.VacunaAplicada;
import com.veterinaria.api.features.vacuna.mapper.VacunaMapper;
import com.veterinaria.api.features.vacuna.repository.InventarioVacunaRepository;
import com.veterinaria.api.features.vacuna.repository.VacunaAplicadaRepository;
import com.veterinaria.api.features.vacuna.service.VacunaService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VacunaServiceImpl implements VacunaService {

    private final VacunaAplicadaRepository vacunaAplicadaRepository;
    private final InventarioVacunaRepository inventarioVacunaRepository;
    private final MascotaRepository mascotaRepository;
    private final VeterinarioRepository veterinarioRepository;
    private final VacunaMapper vacunaMapper;

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "vacunacion_pendiente")
    public List<VacunaPendienteDTO> obtenerVacunacionPendiente() {
        List<Object[]> resultadosRaw = vacunaAplicadaRepository.findVacunacionPendienteRaw();

        return resultadosRaw.stream().map(obj -> new VacunaPendienteDTO(
                (Integer) obj[0],
                (String) obj[1],
                (String) obj[2],
                (String) obj[3],
                (String) obj[4],
                (Integer) obj[5],
                (String) obj[6]
        )).toList();
    }

    @Override
    @Transactional
    @CacheEvict(value = "vacunacion_pendiente", allEntries = true)
    public void aplicarVacuna(VacunaAplicadaRequestDTO request) {
        Mascota mascota = mascotaRepository.findById(request.getMascotaId())
                .orElseThrow(() -> new ResourceNotFoundException("Mascota no encontrada"));

        InventarioVacuna vacuna = inventarioVacunaRepository.findById(request.getVacunaId())
                .orElseThrow(() -> new ResourceNotFoundException("Vacuna no encontrada en inventario"));

        Veterinario veterinario = veterinarioRepository.findById(request.getVeterinarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Veterinario no encontrado"));

        VacunaAplicada nuevaVacuna = vacunaMapper.toEntity(request);
        nuevaVacuna.setMascota(mascota);
        nuevaVacuna.setVacuna(vacuna);
        nuevaVacuna.setVeterinario(veterinario);

        vacunaAplicadaRepository.save(nuevaVacuna);
    }
}