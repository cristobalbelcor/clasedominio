package com.dominio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.ArrayList;

import com.dominio.domain.Individuo;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(@RequestParam(required = false) String q, Model model) {
        List<Individuo> lista = new ArrayList<>();

        Individuo i1 = new Individuo();
        i1.setNombre("Angel");
        i1.setApellido("Ortiz");
        i1.setEdad(25);
        i1.setCorreo("angel@example.com");

        Individuo i2 = new Individuo();
        i2.setNombre("Diana");
        i2.setApellido("Delgado");
        i2.setEdad(24);
        i2.setCorreo("diana@example.com");

        lista.add(i1);
        lista.add(i2);

        if (q != null && !q.trim().isEmpty()) {
            String busqueda = q.trim().toLowerCase();
            List<Individuo> listaFiltrada = new ArrayList<>();
            for (Individuo ind : lista) {
                if (ind.getNombre().toLowerCase().contains(busqueda)) {
                    listaFiltrada.add(ind);
                }
            }
            lista = listaFiltrada;
        }

        model.addAttribute("q", q);
        model.addAttribute("lista", lista);
        return "index";
    }

    @GetMapping("/detalle")
    public String detalle(
        @RequestParam String nombre,
        @RequestParam String apellido,
        @RequestParam int edad,
        @RequestParam String correo,
        Model model
    ) {
        Individuo individuo = new Individuo();
        individuo.setNombre(nombre);
        individuo.setApellido(apellido);
        individuo.setEdad(edad);
        individuo.setCorreo(correo);

        model.addAttribute("individuo", individuo);
        return "detalle";
    }
}
