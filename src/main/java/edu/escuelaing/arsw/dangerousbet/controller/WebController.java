package edu.escuelaing.arsw.dangerousbet.controller;



import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import edu.escuelaing.arsw.dangerousbet.security.dto.JwtDto;
import edu.escuelaing.arsw.dangerousbet.security.dto.LoginUsuario;
import edu.escuelaing.arsw.dangerousbet.security.dto.nuevaSala;
import edu.escuelaing.arsw.dangerousbet.security.entity.Salas;
import edu.escuelaing.arsw.dangerousbet.security.entity.Usuario;
import edu.escuelaing.arsw.dangerousbet.security.service.MonedaService;
import edu.escuelaing.arsw.dangerousbet.security.service.SalasService;
import edu.escuelaing.arsw.dangerousbet.security.service.UsuarioLogrosService;
import edu.escuelaing.arsw.dangerousbet.security.service.UsuarioService;

import org.springframework.web.bind.annotation.*;



@Controller
public class WebController {

	@Autowired
	private UsuarioLogrosService s;
	
	@Autowired
	private MonedaService moneda;
	
	@Autowired
	private UsuarioService usuario;
	
	@Autowired
	private SalasService sala;
    
    @GetMapping("/bienvenido")
    public String registroBienvenido(HttpServletRequest request) {
        if(request.isUserInRole("ROLE_USER")){
            return "redirect:/menu";
        }
        System.out.println("Login exitoso");
        return "redirect:/admin";
    }
    @GetMapping("/monedas/{user}")
    public ResponseEntity<?> getMonedas(@PathVariable("user") String user) {
    	
    	return new ResponseEntity<>(moneda.getMonedas(user),HttpStatus.ACCEPTED);
    }
    
    @GetMapping("/usuario/{user}")
    public ResponseEntity<?> obtenerUsuario(@PathVariable("user") String user) {
    	Usuario u=usuario.getById(user).get();
    	u.setContrasena(null);
    	return new ResponseEntity<>(u,HttpStatus.ACCEPTED);
    }
    
    @PostMapping("/salas")
    public ResponseEntity<?> crearSalas(@RequestBody nuevaSala sala, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity<>("Campos mal puestos", HttpStatus.BAD_REQUEST);
        }
        
        
        this.sala.save(new Salas(this.sala.mayorSala(),sala.getNombre(),sala.getClave(),false));
        
        return new ResponseEntity<>("SALA CREADA", HttpStatus.CREATED);

    }

	
	@GetMapping("/logrosObtenidos/{user}")
    public ResponseEntity<?> logrosObtenidos(@PathVariable("user") String user) {

            return new ResponseEntity<>(s.consultaTrofeos(user),HttpStatus.ACCEPTED);
    }
	
	
    @GetMapping("/logoutSuccessful")
    public String logoutSuccessful(Model model) {
        //System.out.print(model.getAttribute("input1"));
        System.out.println("logout exitoso");
        return "logoutSuccessful";
    }


	@GetMapping("/getLogros/{user}")
    public ResponseEntity<?> getLogros(@PathVariable("user") String user) {	
        return new ResponseEntity<>(s.consultaPractica(user),HttpStatus.ACCEPTED);
    }
    
    @GetMapping("/getMejoresJugadores")
    public ResponseEntity<?> getMejoresJugadores() {	
    	
        return new ResponseEntity<>(moneda.getMejoresPosiciones(),HttpStatus.ACCEPTED);
    }
}
