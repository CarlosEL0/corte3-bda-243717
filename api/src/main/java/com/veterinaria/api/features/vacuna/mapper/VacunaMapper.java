package com.veterinaria.api.features.vacuna.mapper;

import com.veterinaria.api.features.vacuna.dto.VacunaAplicadaRequestDTO;
import com.veterinaria.api.features.vacuna.entity.VacunaAplicada;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VacunaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "mascota", ignore = true)
    @Mapping(target = "vacuna", ignore = true)
    @Mapping(target = "veterinario", ignore = true)
    VacunaAplicada toEntity(VacunaAplicadaRequestDTO dto);
}