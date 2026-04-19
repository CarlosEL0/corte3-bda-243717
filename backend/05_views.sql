-- backend/05_views.sql

-- ==================================================================
-- VISTA: v_mascotas_vacunacion_pendiente
-- ==================================================================
CREATE OR REPLACE VIEW v_mascotas_vacunacion_pendiente AS
SELECT
    m.id AS mascota_id,
    m.nombre AS nombre_mascota,
    m.especie,
    d.nombre AS nombre_dueno,
    d.telefono,
    iv.id AS vacuna_id,
    iv.nombre AS vacuna_pendiente
FROM mascotas m
         JOIN duenos d ON m.dueno_id = d.id
-- Hacemos un CROSS JOIN para combinar todas las mascotas con todas las vacunas
         CROSS JOIN inventario_vacunas iv
-- Filtramos usando LEFT JOIN buscando aquellas que NO estén en vacunas_aplicadas
         LEFT JOIN vacunas_aplicadas va
                   ON va.mascota_id = m.id AND va.vacuna_id = iv.id
WHERE va.id IS NULL;