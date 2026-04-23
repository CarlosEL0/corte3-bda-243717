package com.veterinaria.api.features.usuario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DuenoRequestDTO {

    @NotBlank(message = "El nombre del dueño es requerido")
    private String nombre;

    @Email(message = "Debe ser un formato de correo electrónico válido")
    private String email;

    @NotBlank(message = "El teléfono es requerido")
    private String telefono;
}