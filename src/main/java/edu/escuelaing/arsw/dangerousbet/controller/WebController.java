package edu.escuelaing.arsw.dangerousbet.controller;



import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;



@Controller
public class WebController {


//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody Usuario user) {
//        try {
//            return new ResponseEntity<>(service.login(user),HttpStatus.ACCEPTED);
//        } catch (Exception e) {
//            Logger.getLogger(WebController.class.getName()).log(Level.SEVERE, null, e);
//            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @GetMapping("/registro")
//    public String registro(Model model) {
//        System.out.print(model.getAttribute("input1"));
//        model.addAttribute("usuario", new Usuario());
//        return "formulario";
//    }
    
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
            return new ResponseEntity<>(5000,HttpStatus.ACCEPTED);
    }
//
//    @PostMapping("/registro")
//    public ResponseEntity<?> registro(@RequestBody Usuario user) {
//        try {
//            return new ResponseEntity<>(service.save(user),HttpStatus.CREATED);
//        } catch (Exception e) {
//            Logger.getLogger(WebController.class.getName()).log(Level.SEVERE, null, e);
//            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
//        }
//    }

    @GetMapping("/logoutSuccessful")
    public String logoutSuccessful(Model model) {
        //System.out.print(model.getAttribute("input1"));
        System.out.println("logout exitoso");
        return "logoutSuccessful";
    }
//
//    @GetMapping("/admin")
//    public String adminBienvenido(Model model) {
//        System.out.println("Admin");
//        return "admin";
//    }
//    @GetMapping("/user")
//    public String userBienvenido(Model model) {
//        System.out.println("user");
//        return "user";
//    }
//
//    @GetMapping("/salas")
//    public String salaBienvenido(Model model) {
//        System.out.println("user");
//        return "salas";
//    }
//    @GetMapping("/crearSalas")
//    public String crearSalaBienvenido(Model model) {
//        System.out.println("user");
//        return "crearSalas";
//    }
    @GetMapping("/menu")
    public String userMenu(Model model) {
        return "menu";
    }
	@GetMapping("/logros")
    public String logrosMenu(Model model) {
        return "logros";
    }
	@PostMapping("/vista")
//    public String registroSala(@Valid Usuario usuario,BindingResult bindingResult) {
//
//     		return "vista";
//
//    }
	@GetMapping("/vista")
    public String visaPoker(Model model) {
        return "vista";
    }
//	@GetMapping("/perfil")
//    public String verPerfil(Model model) {
//        return "perfil";
//    }
	@GetMapping("/getLogros")
    public ResponseEntity<?> getLogros() {	
		ArrayList<String> lista=new ArrayList<>();
		lista.add("cuadrado.png");
		lista.add("cuadrado.png");
		lista.add("cuadrado.png");
        return new ResponseEntity<>(lista,HttpStatus.ACCEPTED);
    }
}
