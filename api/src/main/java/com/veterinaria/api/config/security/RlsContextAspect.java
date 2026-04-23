package com.veterinaria.api.config.security;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Aspect
@Component
public class RlsContextAspect {

    @PersistenceContext
    private EntityManager entityManager;

    // Este Pointcut intercepta todos los métodos dentro del paquete 'service.impl'

    @Before("execution(* com.veterinaria.api.features.*.service.impl.*.*(..))")
    @Transactional
    public void setSessionContext() {
        Integer userId = UserContextHolder.getUserId();

        Session session = entityManager.unwrap(Session.class);

        if (userId != null) {

            session.doWork(connection -> {
                try (var statement = connection.createStatement()) {

                    statement.execute("SET ROLE rol_veterinario");

                    statement.execute("SET LOCAL app.current_user_id = '" + userId + "'");
                }
            });
        } else {
            // Si no hay ID (p. ej. Recepción o Admin), reseteamos o usamos un rol por defecto
            session.doWork(connection -> {
                try (var statement = connection.createStatement()) {
                    // Para simplificar, si no hay header X-User-Id, asumimos rol admin/recepción 
                    statement.execute("RESET ROLE");
                }
            });
        }
    }
}