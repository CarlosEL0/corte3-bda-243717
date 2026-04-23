package com.veterinaria.api.features.mascota.mapper;

import com.veterinaria.api.features.mascota.dto.MascotaRequestDTO;
import com.veterinaria.api.features.mascota.dto.MascotaResponseDTO;
import com.veterinaria.api.features.mascota.entity.Mascota;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MascotaMapper {

    // Mapeo de Entidad a DTO
    @Mapping(source = "dueno.id", target = "duenoId")
    @Mapping(source = "dueno.nombre", target = "nombreDueno")
    MascotaResponseDTO toDTO(Mascota mascota);

    // Mapeo de DTO a Entidad
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dueno", ignore = true)
    Mascota toEntity(MascotaRequestDTO dto);
}