package com.dominio.controller;

import com.dominio.dao.IndividuoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dominio.domain.Individuo;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    //Busca el objeto que existe como bind
    @Autowired
    private IndividuoDao individuoDao;
    @GetMapping("/")
    public String index(@RequestParam(required = false) String q, Model model){

        List<Individuo> lista = new ArrayList<>();
        individuoDao.findAll().forEach(lista::add);

        if (q != null && !q.trim().isEmpty()) {
            String busqueda = q.trim().toLowerCase();
            List<Individuo> listaFiltrada = new ArrayList<>();
            for (Individuo ind : lista) {
                if (ind.getNombre() != null && ind.getNombre().toLowerCase().contains(busqueda)) {
                    listaFiltrada.add(ind);
                }
            }
            lista = listaFiltrada;
        }

        model.addAttribute("q", q);
        model.addAttribute("lista",lista);
        return "index";
    }
    @GetMapping("/detalle")
    public String detalle(@RequestParam("id") Integer id, Model model){
        Individuo individuo = individuoDao.findById(id).orElse(null);
        if (individuo == null) {
            return "redirect:/";
        }
        model.addAttribute("individuo", individuo);
        return "detalle";
    }
    
    

}
