package edu.escuelaing.arsw.dangerousbet.controller;



import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import edu.escuelaing.arsw.dangerousbet.security.jwt.JwtProvider;
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

import edu.escuelaing.arsw.dangerousbet.game.JuegoException;
import edu.escuelaing.arsw.dangerousbet.persistence.SalaPersistenceException;
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
import edu.escuelaing.arsw.dangerousbet.security.service.serviceException;

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
    	try {
    		return new ResponseEntity<>(perfil.getPerfil(user),HttpStatus.ACCEPTED);
    	}catch(Exception e) {
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    }


    @GetMapping("/perfil/token/{user}")
    public ResponseEntity<?> getPerfilToken(@PathVariable("user") String user) {
        try {
            return new ResponseEntity<>(perfil.getPerfilToken(user),HttpStatus.ACCEPTED);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/usuario/{user}")
    public ResponseEntity<?> obtenerUsuario(@PathVariable("user") String user) {
    	Usuario u=usuario.getById(user).get();
    	u.setContrasena(null);
        System.out.println(u);
    	return new ResponseEntity<>(u,HttpStatus.ACCEPTED);
    }

    @GetMapping("/usuario/token/{token}")
    public ResponseEntity<?> obtenerUsuarioToken(@PathVariable("token") String token) {
        System.out.println("================================");
        Usuario u=usuario.getByIdToken(token).get();

        u.setContrasena(null);
        System.out.println(u);
        return new ResponseEntity<>(u,HttpStatus.ACCEPTED);
    }

    @GetMapping("/usuario/token/{token}/{user}")
    public ResponseEntity<?> dueñoSala(@PathVariable("token") String token,@PathVariable("user") String user) {
        System.out.println("================================");
        boolean val=false;
        Usuario u=usuario.getByIdToken(token).get();
        val=(user.equals(u.getNickname()));

        u.setContrasena(null);
        System.out.println(u);
        return new ResponseEntity<>(val,HttpStatus.ACCEPTED);
    }

    
    @PostMapping("/salas")
    public ResponseEntity<?> crearSalas(@RequestBody Salas sala) throws SalaPersistenceException{
    	try {
    		srvall.crearSala(sala);
    		return new ResponseEntity<>(HttpStatus.CREATED);
    	}
    	catch(SalaPersistenceException ex) {
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    }
    
    @PutMapping("/nuevoJugador/{sala}/{clave}")
    public ResponseEntity<?> agregarJugador(@PathVariable("sala") String sala,@PathVariable("clave") String clave,@RequestBody String nj) throws SalaPersistenceException{
    	try {
	    	srvall.agregarJugador(sala,clave,nj);
	    	return new ResponseEntity<>(HttpStatus.ACCEPTED);
    	}catch(SalaPersistenceException ex) {
    		return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    	}

    }
    @PutMapping("/eliminarJugador/{sala}")
    public ResponseEntity<?> eliminarJugador(@PathVariable("sala") String sala,@RequestBody String nj){
    	System.out.println(nj);
    	srvall.eliminarJugador(sala,nj);
    	return new ResponseEntity<>(HttpStatus.ACCEPTED);

    }
    
    @PutMapping("/privacidadSala/{sala}")
    public ResponseEntity<?> privacidadSala(@PathVariable("sala") String sala){

    	srvall.privacidadSala(sala);
    	return new ResponseEntity<>(HttpStatus.ACCEPTED);

    }
    
    
    
    @GetMapping("/investigarSala/{sala}")
    public ResponseEntity<?> investigarSala(@PathVariable("sala") String s) {
    	Salas sala=srvall.obtenerSala(s);
    	if(sala!=null) {
    		return new ResponseEntity<>(sala,HttpStatus.ACCEPTED);
    	}
    	return new ResponseEntity<>("null",HttpStatus.ACCEPTED);
    
    }
    @GetMapping("/obtenerClaveDeAcceso/{sala}/{jugador}")
    public ResponseEntity<?> obtenerClaveDeAcceso(@PathVariable("sala") String s,@PathVariable("jugador") String j) {
    	return new ResponseEntity<>(srvall.obtenerClaveDeAcceso(j, s),HttpStatus.ACCEPTED);
    }
    @GetMapping("/obtenerCartasJugadores/{sala}/{jugador}")
    public ResponseEntity<?> obtenerCartasJugadores(@PathVariable("sala") String s,@PathVariable("jugador") String j) {
    	
    	return new ResponseEntity<>(srvall.obtenerCartasJugador(j, s),HttpStatus.ACCEPTED);
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
    public ResponseEntity<?> comprarLogo(@RequestBody UsuarioTienda ust) throws serviceException{	

    	try {
	    	srvall.comprarLogo(ust);
	        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    	}catch(serviceException sx) {
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
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
    
    @PostMapping("/comenzarJuego")
    public ResponseEntity<?> comenzarJuego(@RequestBody String nameSala) throws serviceException,SalaPersistenceException{	
    		try {
    		
		    	srvall.comenzarJuego(nameSala);
		        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    		}catch(SalaPersistenceException ex) {
    			return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
    		}

    }
    
    @GetMapping("/obtenerPlayer/{sala}/{jugador}")
    public ResponseEntity<?> obtenerPlayer(@PathVariable("sala") String sala, @PathVariable("jugador") String jugador) {		
        return new ResponseEntity<>(srvall.obtenerPlayer(sala, jugador),HttpStatus.ACCEPTED);
    }
    @GetMapping("/obtenerMesa/{sala}")
    public ResponseEntity<?> obtenerMesa(@PathVariable("sala") String sala) {		
        return new ResponseEntity<>(srvall.obtenerMesa(sala),HttpStatus.ACCEPTED);
    }
    
    @PutMapping("/pasarJugador/{sala}")
    public ResponseEntity<?> pasarJugador(@PathVariable("sala") String sala) throws JuegoException{	

    	try {
	    	srvall.pasarJugador(sala);
	        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    	}catch(JuegoException sx) {
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    }
    
    @PutMapping("/apostarJuego/{sala}")
    public ResponseEntity<?> apostarJuego(@PathVariable("sala") String sala, @RequestBody int apuesta) throws JuegoException{	

    	try {
	    	srvall.apostar(sala, apuesta);
			System.out.println(apuesta);
	        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    	}catch(JuegoException sx) {
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    }
    
    @PutMapping("/abandonarJuego/{sala}")
    public ResponseEntity<?> abandonarJuego(@PathVariable("sala") String sala){	
    	srvall.abandonarJuego(sala);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    
    @GetMapping("/recibirMensaje/{sala}/{user}")
    public ResponseEntity<?> recibirMensaje(@PathVariable("sala") String sala, @PathVariable("user") String user) {

    	return new ResponseEntity<>(srvall.recibirMensaje(sala, user),HttpStatus.ACCEPTED);
    }
    
    @PutMapping("/nuevoMensaje/{sala}")
    public ResponseEntity<?> nuevoMensaje(@PathVariable("sala") String sala, @RequestBody String mensaje){

    	srvall.nuevoMensaje(sala, mensaje);
    	return new ResponseEntity<>(HttpStatus.ACCEPTED);

    }
}
