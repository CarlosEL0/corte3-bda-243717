package com.veterinaria.api.features.usuario.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Duenos")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Dueno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column (nullable = false, length = 100)
    private String nombre;

    @Column  (nullable = false, length = 100)
    private String email;

    @Column (nullable = false, length = 20)
    private int telefino;


}
