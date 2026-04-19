-- backend/07_rls.sql

-- ==================================================================
-- HABILITAR RLS EN LAS TABLAS SENSIBLES
-- ==================================================================
ALTER TABLE mascotas ENABLE ROW LEVEL SECURITY;
ALTER TABLE citas ENABLE ROW LEVEL SECURITY;
ALTER TABLE vacunas_aplicadas ENABLE ROW LEVEL SECURITY;

-- ==================================================================
-- POLÍTICAS PARA: mascotas
-- Regla: Vets solo ven mascotas que atienden. Recepción y Admin ven todo.
-- ==================================================================
-- Política para Veterinarios
CREATE POLICY pol_mascotas_vet ON mascotas
    FOR ALL
    TO rol_veterinario
    USING (
        id IN (
            SELECT mascota_id
            FROM vet_atiende_mascota
            -- Leemos el ID del contexto de sesión, de forma segura
            WHERE vet_id = NULLIF(current_setting('app.current_user_id', TRUE), '')::INT
        )
    );

-- Políticas para Admin y Recepción (Acceso total a las filas)
CREATE POLICY pol_mascotas_admin ON mascotas FOR ALL TO rol_admin USING (true);
CREATE POLICY pol_mascotas_recep ON mascotas FOR ALL TO rol_recepcion USING (true);


-- ==================================================================
-- POLÍTICAS PARA: citas
-- Regla: Vets solo ven citas donde son el veterinario asignado. Admin y Recep ven todas.
-- ==================================================================
-- Política para Veterinarios
CREATE POLICY pol_citas_vet ON citas
    FOR ALL
    TO rol_veterinario
    USING (
        veterinario_id = NULLIF(current_setting('app.current_user_id', TRUE), '')::INT
    );

-- Políticas para Admin y Recepción
CREATE POLICY pol_citas_admin ON citas FOR ALL TO rol_admin USING (true);
CREATE POLICY pol_citas_recep ON citas FOR ALL TO rol_recepcion USING (true);


-- ==================================================================
-- POLÍTICAS PARA: vacunas_aplicadas
-- Regla: Vets solo ven vacunas de mascotas que atienden. Admin ve todo.
-- Recepción no tiene políticas porque se denegó desde el GRANT.
-- ==================================================================
-- Política para Veterinarios
CREATE POLICY pol_vacunas_vet ON vacunas_aplicadas
    FOR ALL
    TO rol_veterinario
    USING (
        mascota_id IN (
            SELECT mascota_id
            FROM vet_atiende_mascota
            WHERE vet_id = NULLIF(current_setting('app.current_user_id', TRUE), '')::INT
        )
    );

-- Política para Administrador
CREATE POLICY pol_vacunas_admin ON vacunas_aplicadas FOR ALL TO rol_admin USING (true);

