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
import edu.escuelaing.arsw.dangerousbet.security.dto.NuevoJugador;
import edu.escuelaing.arsw.dangerousbet.security.entity.Salas;
import edu.escuelaing.arsw.dangerousbet.security.entity.Usuario;
import edu.escuelaing.arsw.dangerousbet.security.entity.EnSala;
import edu.escuelaing.arsw.dangerousbet.security.service.EnSalaService;
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
	private EnSalaService es;
	
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
        System.out.println(u);
    	return new ResponseEntity<>(u,HttpStatus.ACCEPTED);
    }
    
    @PostMapping("/salas")
    public ResponseEntity<?> crearSalas(@RequestBody nuevaSala sala, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity<>("Campos mal puestos", HttpStatus.BAD_REQUEST);
        }
        
        
        this.sala.save(new Salas(this.sala.mayorSala(),sala.getValorsala(),sala.getNombre(),sala.getClave(),false));
        
        return new ResponseEntity<>("SALA CREADA", HttpStatus.CREATED);

    }
    
    @PostMapping("/nuevoJugador")
    public ResponseEntity<?> agregarJugador(@RequestBody NuevoJugador nj, BindingResult bindingResult){
        
    	if (bindingResult.hasErrors()){
            return new ResponseEntity<>("Campos mal puestos", HttpStatus.BAD_REQUEST);
        }
    	System.out.println("=====================================================");
    	System.out.println(nj.getContrasena()+" "+nj.getNombreSala()+" "+nj.getNickname());
        if(es.comprobar(nj.getNombreSala(),nj.getContrasena())) {
        	
        	es.save(new EnSala(es.mayorSala(),nj.getNickname(),nj.getNombreSala()));
            return new ResponseEntity<>("JUGADOR AÑADIDO", HttpStatus.CREATED);
        }
        
        System.out.println("hola");
        return new ResponseEntity<>("JUGADOR NO AÑADIDO", HttpStatus.BAD_REQUEST);

    }

    @GetMapping("/investigarSala/{sala}")
    public ResponseEntity<?> investigarSala(@PathVariable("sala") String s) {
    		Salas sa=sala.costoSala(s);
    		if(sa!=null) {
    			return new ResponseEntity<>(sa,HttpStatus.ACCEPTED);
    		}
    		return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
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
