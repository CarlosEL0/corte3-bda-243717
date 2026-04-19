-- backend/03_procedures.sql

-- ==================================================================
-- PROCEDURE: sp_agendar_cita
-- ==================================================================
CREATE OR REPLACE PROCEDURE sp_agendar_cita(
    p_mascota_id INT,
    p_veterinario_id INT,
    p_fecha_hora TIMESTAMP,
    p_motivo TEXT,
    OUT p_cita_id INT
)
LANGUAGE plpgsql
AS $$
BEGIN
    -- 1. Validaciones (Manejo de NULLs)
    IF p_mascota_id IS NULL OR p_veterinario_id IS NULL OR p_fecha_hora IS NULL THEN
        RAISE EXCEPTION 'Los campos mascota, veterinario y fecha son obligatorios.';
END IF;

    -- 2. Inserción segura
INSERT INTO citas (mascota_id, veterinario_id, fecha_hora, motivo, estado)
VALUES (p_mascota_id, p_veterinario_id, p_fecha_hora, p_motivo, 'AGENDADA')
    RETURNING id INTO p_cita_id;

-- 3. Confirmar transacción
COMMIT;

EXCEPTION
    WHEN OTHERS THEN
        -- 4. Revertir cambios en caso de cualquier error relacional o de constraints
        ROLLBACK;
        -- 5. Relanzar el error para que el backend (Spring Boot) se entere y no falle silenciosamente
        RAISE EXCEPTION 'Error al agendar cita: %', SQLERRM;
END;
$$;

-- ==================================================================
-- FUNCTION: fn_total_facturado
-- ==================================================================
CREATE OR REPLACE FUNCTION fn_total_facturado(p_mascota_id INT, p_anio INT)
RETURNS NUMERIC
LANGUAGE plpgsql
AS $$
DECLARE
v_total_citas NUMERIC := 0;
    v_total_vacunas NUMERIC := 0;
BEGIN
SELECT COALESCE(SUM(costo), 0) INTO v_total_citas
FROM citas
WHERE mascota_id = p_mascota_id AND EXTRACT(YEAR FROM fecha_hora) = p_anio AND estado = 'COMPLETADA';

SELECT COALESCE(SUM(costo_cobrado), 0) INTO v_total_vacunas
FROM vacunas_aplicadas
WHERE mascota_id = p_mascota_id AND EXTRACT(YEAR FROM fecha_aplicacion) = p_anio;

RETURN v_total_citas + v_total_vacunas;
EXCEPTION
    WHEN OTHERS THEN
        RAISE EXCEPTION 'Error al calcular la facturación: %', SQLERRM;
END;
$$;