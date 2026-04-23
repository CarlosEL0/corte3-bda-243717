// api/src/main/java/com/veterinaria/api/features/usuario/mapper/DuenoMapper.java
package com.veterinaria.api.features.usuario.mapper;

import com.veterinaria.api.features.usuario.dto.DuenoRequestDTO;
import com.veterinaria.api.features.usuario.dto.DuenoResponseDTO;
import com.veterinaria.api.features.usuario.entity.Dueno;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DuenoMapper {

    // De Entidad a DTO (Automático porque los campos se llaman igual)
    DuenoResponseDTO toDTO(Dueno dueno);

    // De DTO a Entidad (Ignoramos el ID porque la base de datos lo autogenera)
    @Mapping(target = "id", ignore = true)
    Dueno toEntity(DuenoRequestDTO dto);
}