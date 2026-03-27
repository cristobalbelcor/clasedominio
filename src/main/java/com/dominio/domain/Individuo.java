package com.dominio.domain;
import lombok.Data;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


@Data
@Entity
@Table(name= "individuo")


public class Individuo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_individuos")
    private Integer idIndividuo;

    @NotEmpty
    private String nombre;
        @NotEmpty
    private String apellido;
        @NotNull
    private Integer edad;
        @NotEmpty
    private String correo;
        @NotEmpty
    private String telefono;
}
