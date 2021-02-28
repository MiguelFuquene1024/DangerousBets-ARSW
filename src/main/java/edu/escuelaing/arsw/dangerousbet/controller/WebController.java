package edu.escuelaing.arsw.dangerousbet.controller;



import javax.validation.Valid;

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
    
    @GetMapping("/registro/bienvenido")
    public String registroBienvenido(Model model) {
        //System.out.print(model.getAttribute("input1"));
        
        return "bienvenido";
    }
    
    @PostMapping("/registro/enviar")
    public String registroEnviar(@Valid Usuario usuario,BindingResult bindingResult) {
    	if(uc.existsById(usuario.getNickname())) {
    		bindingResult.rejectValue("nickname", "usuario.nickname", "Este nickname ya existe");
    	}
    	//System.out.println(bindingResult.getAllErrors());
    	if(bindingResult.hasErrors()){
          
            return "formulario";
    	}else{
    			
   
    		uc.save(usuario);
    		return "redirect:/registro/bienvenido";
    			

    		
    	}
  
    }


}
