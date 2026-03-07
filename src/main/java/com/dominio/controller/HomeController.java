package com.dominio.controller;

import com.dominio.dao.IndividuoDao;
import com.dominio.service.IndivuosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dominio.domain.Individuo;


@Controller
public class HomeController {

    private final IndividuoDao individuoDao;

    //Busca el objeto que existe como bind
    @Autowired
    public IndivuosService individuosService;

    HomeController(IndividuoDao individuoDao) {
        this.individuoDao = individuoDao;
    }

    @GetMapping("/")
    public String index(Model model){
        var lista = individuosService.listarIndividuos();
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
