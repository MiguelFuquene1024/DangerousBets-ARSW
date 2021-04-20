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
import edu.escuelaing.arsw.dangerousbet.security.entity.Tienda;
import edu.escuelaing.arsw.dangerousbet.security.entity.Usuario;
import edu.escuelaing.arsw.dangerousbet.security.entity.UsuarioTienda;
import edu.escuelaing.arsw.dangerousbet.security.entity.EnSala;
import edu.escuelaing.arsw.dangerousbet.security.entity.Perfil;
import edu.escuelaing.arsw.dangerousbet.security.service.PerfilService;
import edu.escuelaing.arsw.dangerousbet.security.service.ServiceAll;
import edu.escuelaing.arsw.dangerousbet.security.service.TiendaService;
import edu.escuelaing.arsw.dangerousbet.security.service.UsuarioLogrosService;
import edu.escuelaing.arsw.dangerousbet.security.service.UsuarioService;


import org.springframework.web.bind.annotation.*;



@Controller
public class WebController {

	@Autowired
	private UsuarioLogrosService s;
	
	@Autowired
	private TiendaService tienda;
	
	@Autowired
	private PerfilService perfil;
	
	@Autowired
	private UsuarioService usuario;
	

	

	
	@Autowired
	private ServiceAll srvall;
    
    @GetMapping("/bienvenido")
    public String registroBienvenido(HttpServletRequest request) {
        if(request.isUserInRole("ROLE_USER")){
            return "redirect:/menu";
        }
        System.out.println("Login exitoso");
        return "redirect:/admin";
    }
    @GetMapping("/perfil/{user}")
    public ResponseEntity<?> getPerfil(@PathVariable("user") String user) {
    	
    	return new ResponseEntity<>(perfil.getPerfil(user),HttpStatus.ACCEPTED);
    }
    
    @GetMapping("/usuario/{user}")
    public ResponseEntity<?> obtenerUsuario(@PathVariable("user") String user) {
    	Usuario u=usuario.getById(user).get();
    	u.setContrasena(null);
        System.out.println(u);
    	return new ResponseEntity<>(u,HttpStatus.ACCEPTED);
    }
    
    @PostMapping("/salas")
    public ResponseEntity<?> crearSalas(@RequestBody Salas sala){
    	
    	srvall.crearSala(sala);
        return new ResponseEntity<>("SALA CREADA", HttpStatus.CREATED);

    }
    
    @PutMapping("/nuevoJugador/{sala}/{clave}")
    public ResponseEntity<?> agregarJugador(@PathVariable("sala") String sala,@PathVariable("clave") String clave,@RequestBody String nj){

    	srvall.agregarJugador(sala,clave,nj);
    	return new ResponseEntity<>(HttpStatus.ACCEPTED);

    }

    @GetMapping("/investigarSala/{sala}")
    public ResponseEntity<?> investigarSala(@PathVariable("sala") String s) {

    	return new ResponseEntity<>(srvall.obtenerSala(s),HttpStatus.ACCEPTED);
    
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
    	
        return new ResponseEntity<>(perfil.getMejoresPosiciones(),HttpStatus.ACCEPTED);
    }
    @PostMapping("/comprarLogo")
    public ResponseEntity<?> comprarLogo(@RequestBody UsuarioTienda ust) {	


    	srvall.comprarLogo(ust);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    
    @GetMapping("/logosNoComprados/{user}")
    public ResponseEntity<?> logosNoComprados(@PathVariable("user") String user) {		
        return new ResponseEntity<>(tienda.logosNoComprados(user),HttpStatus.ACCEPTED);
    }
    
    @GetMapping("/logosComprados/{user}")
    public ResponseEntity<?> logosComprados(@PathVariable("user") String user) {	

        return new ResponseEntity<>(srvall.logosComprados(user),HttpStatus.ACCEPTED);
    }
  
    
    @GetMapping("/salasPublicas")
    public ResponseEntity<?> salasPublicas() {		
        return new ResponseEntity<>(srvall.getSalasPublicas(),HttpStatus.ACCEPTED);
    }
    
    @PutMapping("/actualizarDatos/{user}")
    public ResponseEntity<?> actualizarDatos(@PathVariable("user") String user,@RequestBody Usuario us) {	
    	srvall.actualizarDatos(us,user);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @PutMapping("/actualizarDatosPerfil/{user}")
    public ResponseEntity<?> actualizarDatosPerfil(@PathVariable("user") String user,@RequestBody Perfil pf) {	
    	srvall.actualizarDatosPerfil(pf,user);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    
   
    

}
