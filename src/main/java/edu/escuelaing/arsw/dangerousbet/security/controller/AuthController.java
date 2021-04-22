package edu.escuelaing.arsw.dangerousbet.security.controller;

import edu.escuelaing.arsw.dangerousbet.security.dto.JwtDto;
import edu.escuelaing.arsw.dangerousbet.security.dto.LoginUsuario;
import edu.escuelaing.arsw.dangerousbet.security.dto.NuevoUsuario;
import edu.escuelaing.arsw.dangerousbet.security.entity.Rol;
import edu.escuelaing.arsw.dangerousbet.security.entity.Usuario;
import edu.escuelaing.arsw.dangerousbet.security.enums.RoleName;
import edu.escuelaing.arsw.dangerousbet.security.jwt.JwtProvider;
import edu.escuelaing.arsw.dangerousbet.security.service.RolService;
import edu.escuelaing.arsw.dangerousbet.security.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/register")
    public ResponseEntity<?> nuevo(@RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult){
        //if(bindingResult.hasErrors()){
         //   return new ResponseEntity<>("Campos mal puestos", HttpStatus.BAD_REQUEST);
        //}
        if(usuarioService.existsById(nuevoUsuario.getNickname())){
            return new ResponseEntity<>("Ese nombre ya Exite", HttpStatus.BAD_REQUEST);
        }
        //Usuario usuario = new Usuario(nuevoUsuario.getName(),passwordEncoder.encode(nuevoUsuario.getContrasena()), nuevoUsuario.getNickname(), nuevoUsuario.getCorreo());
        System.out.println(nuevoUsuario.getContrasena());
        Usuario usuario = new Usuario(nuevoUsuario.getName(),nuevoUsuario.getContrasena(), nuevoUsuario.getNickname(), nuevoUsuario.getCorreo());
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getById(0).get());
        usuario.setRoles(roles);
        usuarioService.save(usuario);
        return new ResponseEntity<>("Usuario creado",HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
        //if (bindingResult.hasErrors()){
         //   return new ResponseEntity<>("Campos mal puestos", HttpStatus.BAD_REQUEST);
        //}
    	//System.out.println(loginUsuario.getNickname()+"   "+loginUsuario.getContrasena());
        //Authentication authentication =
          //      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNickname(),loginUsuario.getContrasena()));
        //SecurityContextHolder.getContext().setAuthentication(authentication);
        //String jwt = jwtProvider.generateToken(authentication);
        //UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        //JwtDto jwtDto= new JwtDto(jwt,userDetails.getUsername(),userDetails.getAuthorities());
        //return new ResponseEntity<>(jwtDto,HttpStatus.CREATED);
    	if(usuarioService.existsById(loginUsuario.getNickname())) {
    		Usuario us=usuarioService.getById(loginUsuario.getNickname()).get();
    		System.out.println(us.getContrasena()+"==="+loginUsuario.getContrasena());
    		if(us.getContrasena().equals(loginUsuario.getContrasena())) {
    			return new ResponseEntity<>(loginUsuario.getNickname(),HttpStatus.ACCEPTED);
    		}
    	}
    	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	
    }
}
