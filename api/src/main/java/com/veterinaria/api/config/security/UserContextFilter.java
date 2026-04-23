package com.veterinaria.api.config.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class UserContextFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Extraemos el ID del usuario (simulando un token de sesión) desde los headers
        String userIdHeader = request.getHeader("X-User-Id");

        try {
            if (userIdHeader != null && !userIdHeader.isEmpty()) {
                UserContextHolder.setUserId(Integer.parseInt(userIdHeader));
            }
            filterChain.doFilter(request, response);
        } finally {
            UserContextHolder.clear();
        }
    }
}