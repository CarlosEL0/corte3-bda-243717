// api/src/main/java/com/veterinaria/api/features/mascota/controller/MascotaController.java
package com.veterinaria.api.features.mascota.controller;

import com.veterinaria.api.features.mascota.dto.MascotaRequestDTO;
import com.veterinaria.api.features.mascota.dto.MascotaResponseDTO;
import com.veterinaria.api.features.mascota.service.MascotaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mascotas")
@RequiredArgsConstructor
public class MascotaController { // <-- ¡Corregido el nombre de la clase!

    private final MascotaService mascotaService;

    @PostMapping
    public ResponseEntity<MascotaResponseDTO> crearMascota(@Valid @RequestBody MascotaRequestDTO request) {
        return new ResponseEntity<>(mascotaService.crearMascota(request), HttpStatus.CREATED);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<MascotaResponseDTO>> buscarMascotas(@RequestParam String nombre) {
        return ResponseEntity.ok(mascotaService.buscarPorNombre(nombre));
    }
}