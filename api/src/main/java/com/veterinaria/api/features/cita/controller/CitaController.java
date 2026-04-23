// api/src/main/java/com/veterinaria/api/features/cita/controller/CitaController.java
package com.veterinaria.api.features.cita.controller;

import com.veterinaria.api.features.cita.dto.CitaRequestDTO;
import com.veterinaria.api.features.cita.service.CitaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/citas")
@RequiredArgsConstructor
public class CitaController {

    private final CitaService citaService;

    @PostMapping("/agendar")
    public ResponseEntity<Integer> agendar(@Valid @RequestBody CitaRequestDTO request) {
        return new ResponseEntity<>(citaService.agendarCita(request), HttpStatus.CREATED);
    }
}