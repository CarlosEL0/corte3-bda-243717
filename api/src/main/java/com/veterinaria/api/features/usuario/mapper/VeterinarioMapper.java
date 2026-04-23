// api/src/main/java/com/veterinaria/api/features/usuario/mapper/VeterinarioMapper.java
package com.veterinaria.api.features.usuario.mapper;

import com.veterinaria.api.features.usuario.dto.VeterinarioRequestDTO;
import com.veterinaria.api.features.usuario.dto.VeterinarioResponseDTO;
import com.veterinaria.api.features.usuario.entity.Veterinario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VeterinarioMapper {

    // De Entidad a DTO
    VeterinarioResponseDTO toDTO(Veterinario veterinario);

    // De DTO a Entidad
    @Mapping(target = "id", ignore = true)
    Veterinario toEntity(VeterinarioRequestDTO dto);
}