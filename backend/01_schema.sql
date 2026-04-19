-- backend/01_schema.sql
-- =============================================================
-- ESTRUCTURA DE LA BASE DE DATOS - CLÍNICA VETERINARIA
-- =============================================================

DROP TABLE IF EXISTS alertas               CASCADE;
DROP TABLE IF EXISTS historial_movimientos CASCADE;
DROP TABLE IF EXISTS vacunas_aplicadas     CASCADE;
DROP TABLE IF EXISTS inventario_vacunas    CASCADE;
DROP TABLE IF EXISTS citas                 CASCADE;
DROP TABLE IF EXISTS vet_atiende_mascota   CASCADE;
DROP TABLE IF EXISTS mascotas              CASCADE;
DROP TABLE IF EXISTS veterinarios          CASCADE;
DROP TABLE IF EXISTS duenos                CASCADE;

CREATE TABLE duenos (
                        id        SERIAL PRIMARY KEY,
                        nombre    VARCHAR(100) NOT NULL,
                        telefono  VARCHAR(20),
                        email     VARCHAR(100)
);

CREATE TABLE veterinarios (
                              id              SERIAL PRIMARY KEY,
                              nombre          VARCHAR(100) NOT NULL,
                              cedula          VARCHAR(20) NOT NULL UNIQUE,
                              dias_descanso   VARCHAR(50) DEFAULT '',
                              activo          BOOLEAN DEFAULT TRUE
);

CREATE TABLE mascotas (
                          id                SERIAL PRIMARY KEY,
                          nombre            VARCHAR(50) NOT NULL,
                          especie           VARCHAR(30) NOT NULL,
                          fecha_nacimiento  DATE,
                          dueno_id          INT NOT NULL REFERENCES duenos(id)
);

CREATE TABLE vet_atiende_mascota (
                                     id                      SERIAL PRIMARY KEY,
                                     vet_id                  INT NOT NULL REFERENCES veterinarios(id),
                                     mascota_id              INT NOT NULL REFERENCES mascotas(id),
                                     fecha_inicio_atencion   DATE NOT NULL DEFAULT CURRENT_DATE,
                                     activa                  BOOLEAN DEFAULT TRUE,
                                     UNIQUE (vet_id, mascota_id)
);

CREATE INDEX idx_vam_vet      ON vet_atiende_mascota(vet_id);
CREATE INDEX idx_vam_mascota  ON vet_atiende_mascota(mascota_id);

CREATE TABLE citas (
                       id              SERIAL PRIMARY KEY,
                       mascota_id      INT NOT NULL REFERENCES mascotas(id),
                       veterinario_id  INT NOT NULL REFERENCES veterinarios(id),
                       fecha_hora      TIMESTAMP NOT NULL,
                       motivo          TEXT,
                       costo           NUMERIC(10, 2),
                       estado          VARCHAR(20) DEFAULT 'AGENDADA'
                           CHECK (estado IN ('AGENDADA', 'COMPLETADA', 'CANCELADA'))
);

CREATE TABLE inventario_vacunas (
                                    id              SERIAL PRIMARY KEY,
                                    nombre          VARCHAR(80) NOT NULL,
                                    stock_actual    INT NOT NULL DEFAULT 0 CHECK (stock_actual >= 0),
                                    stock_minimo    INT NOT NULL DEFAULT 5,
                                    costo_unitario  NUMERIC(10, 2) NOT NULL
);

CREATE TABLE vacunas_aplicadas (
                                   id                  SERIAL PRIMARY KEY,
                                   mascota_id          INT NOT NULL REFERENCES mascotas(id),
                                   vacuna_id           INT NOT NULL REFERENCES inventario_vacunas(id),
                                   veterinario_id      INT NOT NULL REFERENCES veterinarios(id),
                                   fecha_aplicacion    DATE NOT NULL DEFAULT CURRENT_DATE,
                                   costo_cobrado       NUMERIC(10, 2)
);

CREATE TABLE historial_movimientos (
                                       id              SERIAL PRIMARY KEY,
                                       tipo            VARCHAR(30) NOT NULL,
                                       referencia_id   INT,
                                       descripcion     TEXT,
                                       fecha           TIMESTAMP DEFAULT NOW()
);

CREATE TABLE alertas (
                         id              SERIAL PRIMARY KEY,
                         tipo            VARCHAR(30) NOT NULL,
                         descripcion     TEXT,
                         fecha           TIMESTAMP DEFAULT NOW()
);