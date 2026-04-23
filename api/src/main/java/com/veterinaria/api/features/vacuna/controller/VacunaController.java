// api/src/main/java/com/veterinaria/api/features/vacuna/controller/VacunaController.java
package com.veterinaria.api.features.vacuna.controller;

import com.veterinaria.api.features.vacuna.dto.VacunaAplicadaRequestDTO;
import com.veterinaria.api.features.vacuna.dto.VacunaPendienteDTO;
import com.veterinaria.api.features.vacuna.service.VacunaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vacunas")
@RequiredArgsConstructor
public class VacunaController {

    private final VacunaService vacunaService;

    @GetMapping("/pendientes")
    public ResponseEntity<List<VacunaPendienteDTO>> listarPendientes() {
        return ResponseEntity.ok(vacunaService.obtenerVacunacionPendiente());
    }

    @PostMapping("/aplicar")
    public ResponseEntity<Void> aplicar(@Valid @RequestBody VacunaAplicadaRequestDTO request) {
        vacunaService.aplicarVacuna(request);
        return ResponseEntity.noContent().build();
    }
}