package com.dominio.controller;

import com.dominio.service.IIndividuoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import com.dominio.domain.Individuo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TransferQueue;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@Controller
public class HomeController {

    @Autowired
    private IIndividuoService individuoService;

    @GetMapping("/detalle")
    public String detalle(@RequestParam("id") Integer id, Model model) {
        Individuo individuo = individuoService.buscarIndividuoPorId(id);
        if (individuo == null) {
            return "redirect:/";
        }
        model.addAttribute("individuo", individuo);
        return "detalle";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("individuo", new Individuo());
        return "form";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Individuo individuo, BindingResult resultado) {
        if (resultado.hasErrors()) {
            return "form";
        }
        individuoService.guardarIndividuo(individuo);
        return "redirect:/";
    }

    @GetMapping("/editar")
    public String editar(@RequestParam("id") Integer id, Model model) {
        Individuo individuo = individuoService.buscarIndividuoPorId(id);
        if (individuo == null) {
            return "redirect:/";
        }
        model.addAttribute("individuo", individuo);
        return "form";
    }

    @GetMapping("/eliminar")
    public String eliminar(@RequestParam("id") Integer id) {
        individuoService.eliminarIndividuo(id);
        return "redirect:/";
    }

    @GetMapping("/")
    public String index(Model model, Authentication authentication,
            @RequestParam(value = "q", required = false) String q) {
        List<Individuo> listaFull = individuoService.listarIndividuos();
        List<Individuo> lista = new ArrayList<>();

        if (q != null && !q.trim().isEmpty()) {
            for (Individuo i : listaFull) {
                if (i.getNombre() != null && i.getNombre().toLowerCase().contains(q.toLowerCase())) {
                    lista.add(i);
                }
            }
        } else {
            lista.addAll(listaFull);
        }

        model.addAttribute("lista", lista);
        model.addAttribute("q", q);
        boolean esAdmin = false;
        String usuarioActual = "No autenticado";
        String rolesActuales = "Sin roles";
        if (authentication != null) {
            usuarioActual = authentication.getName();
            rolesActuales = authentication.getAuthorities().toString();

            for (GrantedAuthority authority : authentication.getAuthorities()) {
                if (authority.getAuthority().equals("ROLE_ADMIN")) {
                    esAdmin = true;
                    break;
                }
            }
        }
        model.addAttribute("esAdmin", esAdmin);
        model.addAttribute("usuarioActual", usuarioActual);
        model.addAttribute("rolesActuales", rolesActuales);

        return "index";

    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/exportar-excel")
    public void exportarExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        response.setHeader("content-Disposition", "attachment; filename-individuos.xlsx");

        List<Individuo> lista = individuoService.listarIndividuos();

        XSSFWorkbook libro = new XSSFWorkbook();
        XSSFSheet hoja = libro.createSheet("Individuo");

        Row filaTitulo = hoja.createRow("0");
        filaTitulo.createCell(0).setCellValue("id");
        filaTitulo.createCell(1).setCellValue("nombre");
        filaTitulo.createCell(2).setCellValue("apellido");
        filaTitulo.createCell(3).setCellValue("edad");
        filaTitulo.createCell(4).setCellValue("correo");
        filaTitulo.createCell(5).setCellValue("telefono");

        int numeroFila =1;
        for(Individuo individuo : lista){
            filaTitulo.createCell(0).setCellValue(individuo.getIdIndividuo());
            filaTitulo.createCell(1).setCellValue(individuo.getNombre());
            filaTitulo.createCell(2).setCellValue(individuo.getApellido());
            filaTitulo.createCell(3).setCellValue(individuo.getEdad());
            filaTitulo.createCell(4).setCellValue(individuo.getCorreo());
            filaTitulo.createCell(5).setCellValue(individuo.getTelefono());
        }

        for (int i = 0; i < 6; i++) {
            hoja.autoSizeColumn(i);
            
        }
        libro.write(response.getOutputStream());
        libro.close();

    }

}
