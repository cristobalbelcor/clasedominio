package com.dominio.domain;

import jakarta.persistence.*; //mapear a las bases de datos
import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import lombok.Data;

@Data
@Entity
@Table
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_perfil")
    private Integer id_perfil;
    private String nombre;
    
}
