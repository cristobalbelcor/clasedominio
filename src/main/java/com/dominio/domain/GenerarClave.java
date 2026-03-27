package com.dominio.domain;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class GenerarClave {
    public static void main(String[]args){
        org.springframework.security.crypto.password.PasswordEncoder encoder = new BCryptPasswordEncoder();

        System.out.println("Clave admin: " + encoder.encode("54321"));
        System.out.println("Clave secretaria: " + encoder.encode("12345"));
        System.out.println("Clave vendedor: " + encoder.encode("12345"));
    }
    
}
