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

import edu.escuelaing.arsw.dangerousbet.security.service.UsuarioLogrosService;

import org.springframework.web.bind.annotation.*;



@Controller
public class WebController {

	@Autowired
	private UsuarioLogrosService s;
    
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

	
	@GetMapping("/logrosObtenidos")
    public ResponseEntity<?> logrosObtenidos() {

            return new ResponseEntity<>(2,HttpStatus.ACCEPTED);
    }



    @GetMapping("/logoutSuccessful")
    public String logoutSuccessful(Model model) {
        //System.out.print(model.getAttribute("input1"));
        System.out.println("logout exitoso");
        return "logoutSuccessful";
    }



	@GetMapping("/getLogros")
    public ResponseEntity<?> getLogros() {	
		ArrayList<String> lista=new ArrayList<>();
		s.consultaPractica();
        return new ResponseEntity<>(s.consultaPractica(),HttpStatus.ACCEPTED);
    }
}
