package com.veterinaria.api.config.security;

public class UserContextHolder {

    // Guarda el ID del usuario asociado al hilo actual (petición web)
    private static final ThreadLocal<Integer> contextHolder = new ThreadLocal<>();

    public static void setUserId(Integer userId) {
        contextHolder.set(userId);
    }

    public static Integer getUserId() {
        return contextHolder.get();
    }

    public static void clear() {
        contextHolder.remove();
    }
}