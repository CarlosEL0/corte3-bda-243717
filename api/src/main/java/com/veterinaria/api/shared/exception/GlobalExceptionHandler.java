// api/src/main/java/com/veterinaria/api/exception/GlobalExceptionHandler.java
package com.veterinaria.api.shared.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // Captura cualquier excepción no controlada
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleAllUncaughtException(Exception ex) {
        // 1. Log detallado para el backend
        logger.error("Error interno del servidor capturado: ", ex);

        // 2. Respuesta genérica para el frontend (el atacante o usuario no ve detalles)
        Map<String, String> response = new HashMap<>();
        response.put("error", "internal_server_error");
        response.put("message", "Ha ocurrido un error inesperado. Por favor, contacte a soporte.");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}