package com.veterinaria.api.features.cita.mapper;

import com.veterinaria.api.features.cita.dto.CitaRequestDTO;
import com.veterinaria.api.features.cita.dto.CitaResponseDTO;
import com.veterinaria.api.features.cita.entity.Cita;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CitaMapper {

    @Mapping(source = "mascota.nombre", target = "nombreMascota")
    @Mapping(source = "veterinario.nombre", target = "nombreVeterinario")
    CitaResponseDTO toDTO(Cita cita);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "mascota", ignore = true)
    @Mapping(target = "veterinario", ignore = true)
    @Mapping(target = "costo", ignore = true)
    @Mapping(target = "estado", constant = "AGENDADA")
    Cita toEntity(CitaRequestDTO dto);
}