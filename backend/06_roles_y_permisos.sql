-- backend/06_roles_y_permisos.sql

-- 1. Eliminar roles si existen
DROP ROLE IF EXISTS rol_admin;
DROP ROLE IF EXISTS rol_recepcion;
DROP ROLE IF EXISTS rol_veterinario;

-- 2. Crear Roles
CREATE ROLE rol_admin;
CREATE ROLE rol_recepcion;
CREATE ROLE rol_veterinario;

-- ==================================================================
-- PERMISOS PARA ADMINISTRADOR
-- ==================================================================
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO rol_admin;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO rol_admin;

-- ==================================================================
-- PERMISOS PARA RECEPCIÓN (Least Privilege)
-- ==================================================================
GRANT USAGE ON SCHEMA public TO rol_recepcion;
-- Tienen acceso a dueños y mascotas
GRANT SELECT, INSERT, UPDATE ON duenos, mascotas TO rol_recepcion;
-- Pueden agendar y ver citas
GRANT SELECT, INSERT, UPDATE ON citas TO rol_recepcion;
-- Necesitan acceso a las secuencias para hacer INSERTs
GRANT USAGE, SELECT ON SEQUENCE duenos_id_seq, mascotas_id_seq, citas_id_seq TO rol_recepcion;

-- EXCLUSIÓN EXPLÍCITA: Recepción NO debe ver esta tabla.
REVOKE ALL PRIVILEGES ON vacunas_aplicadas FROM rol_recepcion;
-- (Opcional) Denegamos acceso al inventario médico por si acaso
REVOKE ALL PRIVILEGES ON inventario_vacunas FROM rol_recepcion;

-- ==================================================================
-- PERMISOS PARA VETERINARIO (Least Privilege)
-- ==================================================================
GRANT USAGE ON SCHEMA public TO rol_veterinario;
-- Ven dueños, mascotas e inventario (select)
GRANT SELECT ON duenos, mascotas, inventario_vacunas TO rol_veterinario;
-- Pueden registrar citas y aplicar vacunas
GRANT SELECT, INSERT, UPDATE ON citas, vacunas_aplicadas TO rol_veterinario;
GRANT USAGE, SELECT ON SEQUENCE citas_id_seq, vacunas_aplicadas_id_seq TO rol_veterinario;

-- Acceso explícito a la tabla pivote para el RLS posterior
GRANT SELECT ON vet_atiende_mascota TO rol_veterinario;