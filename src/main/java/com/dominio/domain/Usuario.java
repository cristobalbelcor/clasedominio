package com.dominio.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id_usuario")
    private Integer id_usuario;
    private String username;
    private String password;
    private String rol;
    private String actividad;

    @ManyToOne

    @JoinColumn(name = "id_perfil")
    private Perfil perfil;
}
