-- backend/04_triggers.sql

-- ==================================================================
-- FUNCIÓN DEL TRIGGER
-- ==================================================================
CREATE OR REPLACE FUNCTION fn_trg_historial_cita()
RETURNS TRIGGER
LANGUAGE plpgsql
AS $$
BEGIN
INSERT INTO historial_movimientos (tipo, referencia_id, descripcion, fecha)
VALUES (
           'NUEVA_CITA',
           NEW.id,
           FORMAT('Se agendó cita para la mascota %s con el veterinario %s', NEW.mascota_id, NEW.veterinario_id),
           NOW()
       );
RETURN NEW;
END;
$$;

-- ==================================================================
-- TRIGGER: trg_historial_cita
-- ==================================================================
CREATE TRIGGER trg_historial_cita
    AFTER INSERT ON citas
    FOR EACH ROW
    EXECUTE FUNCTION fn_trg_historial_cita();