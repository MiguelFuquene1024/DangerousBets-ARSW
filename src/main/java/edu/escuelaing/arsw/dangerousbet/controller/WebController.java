package edu.escuelaing.arsw.dangerousbet.controller;



import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import edu.escuelaing.arsw.dangerousbet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	@Autowired
    private UserDao uc;
	
	
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
            return "redirect:/menu";
        }
        System.out.println("Login exitoso");
        return "redirect:/admin";
    }
    @GetMapping("/monedas")
    public ResponseEntity<?> getMonedas() {
    		System.out.println("20002222222222222220202020200220020");
            return new ResponseEntity<>(service.getMonedas(),HttpStatus.ACCEPTED);
        
    }
    
    @PostMapping("/registro")
    public String registroEnviar(@Valid Usuario usuario,BindingResult bindingResult) {
    	if(uc.existsById(usuario.getNickname())) {
    		bindingResult.rejectValue("nickname", "usuario.nickname", "Este nickname ya existe");
    	}
    	if(bindingResult.hasErrors()){
            return "formulario";
    	}else{
    		uc.save(usuario);
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
    @GetMapping("/menu")
    public String userMenu(Model model) {
        return "menu";
    }
}
