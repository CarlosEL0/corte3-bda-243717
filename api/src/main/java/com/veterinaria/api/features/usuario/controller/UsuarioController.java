// api/src/main/java/com/veterinaria/api/features/usuario/controller/UsuarioController.java
package com.veterinaria.api.features.usuario.controller;

import com.veterinaria.api.features.usuario.dto.DuenoRequestDTO;
import com.veterinaria.api.features.usuario.dto.DuenoResponseDTO;
import com.veterinaria.api.features.usuario.dto.VeterinarioRequestDTO;
import com.veterinaria.api.features.usuario.dto.VeterinarioResponseDTO;
import com.veterinaria.api.features.usuario.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/duenos")
    public ResponseEntity<DuenoResponseDTO> registrarDueno(@Valid @RequestBody DuenoRequestDTO request) {
        return new ResponseEntity<>(usuarioService.guardarDueno(request), HttpStatus.CREATED);
    }

    @GetMapping("/duenos/{id}")
    public ResponseEntity<DuenoResponseDTO> obtenerDueno(@PathVariable Integer id) {
        return ResponseEntity.ok(usuarioService.obtenerDuenoPorId(id));
    }

    @PostMapping("/veterinarios")
    public ResponseEntity<VeterinarioResponseDTO> registrarVeterinario(@Valid @RequestBody VeterinarioRequestDTO request) {
        return new ResponseEntity<>(usuarioService.guardarVeterinario(request), HttpStatus.CREATED);
    }

    @GetMapping("/veterinarios/cedula/{cedula}")
    public ResponseEntity<VeterinarioResponseDTO> obtenerVeterinarioPorCedula(@PathVariable String cedula) {
        return ResponseEntity.ok(usuarioService.obtenerVeterinarioPorCedula(cedula));
    }
}