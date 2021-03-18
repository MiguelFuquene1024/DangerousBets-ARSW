package edu.escuelaing.arsw.dangerousbet.controller;



import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import edu.escuelaing.arsw.dangerousbet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;

import edu.escuelaing.arsw.dangerousbet.dao.UserDao;
import edu.escuelaing.arsw.dangerousbet.model.Usuario;


@Controller
public class WebController {

	@Autowired
    private UserService service;
	
	
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
    
    @GetMapping("/bienvenido")
    public String registroBienvenido(HttpServletRequest request) {
        if(request.isUserInRole("ROLE_USER")){
            return "redirect:/user";
        }
        System.out.println("Login exitoso");
        return "redirect:/admin";
    }
    
    @PostMapping("/registro")
    public String registroEnviar(@Valid Usuario usuario,BindingResult bindingResult) {
        if(!service.save(usuario)){
            bindingResult.rejectValue("nickname", "usuario.nickname", "Este nickname ya existe");
        }
    	if(bindingResult.hasErrors()){
            return "formulario";
    	}else{
     		return "bienvenido";
    	}
  
    }

    @GetMapping("/logoutSuccessful")
    public String logoutSuccessful(Model model) {
        //System.out.print(model.getAttribute("input1"));
        System.out.println("logout exitoso");
        return "logoutSuccessful";
    }

    @GetMapping("/admin")
    public String adminBienvenido(Model model) {
        System.out.println("Admin");
        return "admin";
    }
    @GetMapping("/user")
    public String userBienvenido(Model model) {
        System.out.println("user");
        return "user";
    }
    @GetMapping("/salas")
    public String salaBienvenido(Model model) {
        System.out.println("user");
        return "salas";
    }
    @GetMapping("/crearSalas")
    public String crearSalaBienvenido(Model model) {
        System.out.println("user");
        return "crearSalas";
    }
}
