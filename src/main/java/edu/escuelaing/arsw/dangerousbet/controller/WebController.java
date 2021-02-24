package edu.escuelaing.arsw.dangerousbet.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import edu.eci.arsw.blueprints.model.Usuario;


@Controller
public class WebController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("hora","buenos dias a todos este es la primera aplicacion");
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        //System.out.print(model.getAttribute("input1"));
        
        return "login";
    }

    @GetMapping("/registro")
    public String registro(Model model) {
        System.out.print(model.getAttribute("input1"));
        model.addAttribute("usuario", new Usuario());
        return "formulario";
    }
    
    @GetMapping("/registro/bienvenido")
    public String registroBienvenido(Model model) {
        //System.out.print(model.getAttribute("input1"));
        
        return "bienvenido";
    }
    
    @PostMapping("/registro/enviar")
    public String registroEnviar(Usuario usuario,Model model) {
 
        return "redirect:/registro/bienvenido";
    }


}
