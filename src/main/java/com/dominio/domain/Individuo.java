package com.dominio.domain;
import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name= "individuo")


public class Individuo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_individuos")
    private Integer idIndividuo;

    private String nombre;
    private String apellido;
    private int edad;
    private String correo;
}
