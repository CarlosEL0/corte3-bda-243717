package com.veterinaria.api.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CacheLoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(CacheLoggingAspect.class);
    private final CacheManager cacheManager;

    public CacheLoggingAspect(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    // Interceptamos la consulta de vacunas pendientes
    @Around("execution(* com.veterinaria.api.features.vacuna.controller.VacunaController.listarPendientes(..))")
    public Object logCacheHitOrMiss(ProceedingJoinPoint joinPoint) throws Throwable {
        Cache cache = cacheManager.getCache("vacunacion_pendiente");

        if (cache != null && cache.get(SimpleKey.EMPTY) != null) {
            logger.info("[CACHE HIT] vacunacion_pendiente -> Obteniendo datos desde Redis (~5-20ms)");
        } else {
            logger.info("[CACHE MISS] vacunacion_pendiente -> Consultando a PostgreSQL y guardando en Redis (~100-300ms)");
        }

        return joinPoint.proceed();
    }

    // Interceptamos cuando se aplica una vacuna nueva
    @Around("execution(* com.veterinaria.api.features.vacuna.controller.VacunaController.aplicar(..))")
    public Object logCacheInvalidation(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("[CACHE INVALIDATION] vacunacion_pendiente -> Limpiando caché por nueva vacuna aplicada");
        return joinPoint.proceed();
    }
}