package edu.escuelaing.arsw.dangerousbet.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import edu.escuelaing.arsw.dangerousbet.model.Usuario;


@Controller
public class WebController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("hora","buenos dias a todos este es la primera aplicacion");
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("usuario", new Usuario());;
        
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
<<<<<<< HEAD
 
        return "redirect:/registro/bienvenido";
=======
        System.out.print("\n"+usuario.getNickname());
        System.out.print("\n"+usuario.getContrasena());

 
        return "index";
>>>>>>> 16f263ba8ae37ab933bd7f84950de5e933cac8d8
    }


}
