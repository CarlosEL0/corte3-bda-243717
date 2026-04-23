package com.veterinaria.api.features.vacuna.entity;

import com.veterinaria.api.features.mascota.entity.Mascota;
import com.veterinaria.api.features.usuario.entity.Veterinario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "vacunas_aplicadas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacunaAplicada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mascota_id", nullable = false)
    private Mascota mascota;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vacuna_id", nullable = false)
    private InventarioVacuna vacuna;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "veterinario_id", nullable = false)
    private Veterinario veterinario;

    @Column(name = "fecha_aplicacion", nullable = false)
    private LocalDate fechaAplicacion;

    @Column(name = "costo_cobrado")
    private BigDecimal costoCobrado;
}