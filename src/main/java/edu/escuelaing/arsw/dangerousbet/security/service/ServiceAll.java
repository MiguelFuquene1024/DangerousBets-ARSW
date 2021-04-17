package edu.escuelaing.arsw.dangerousbet.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import edu.escuelaing.arsw.dangerousbet.security.entity.Tienda;
import edu.escuelaing.arsw.dangerousbet.security.entity.UsuarioTienda;


@Controller
public class ServiceAll {
	
	@Autowired
	private UsuarioLogrosService s;
	
	@Autowired
	private TiendaService tienda;
	
	@Autowired
	private UsuarioTiendaService ust;
	
	@Autowired
	private PerfilService perfil;
	
	@Autowired
	private UsuarioService usuario;
	
	@Autowired
	private EnSalaService es;
	
	@Autowired
	private SalasService sala;

	public void comprarLogo(UsuarioTienda ut) {

		int numero = ust.mayorIdUsuarioLogro();
		ust.save(new UsuarioTienda(numero,ut.getUsuario(),ut.getTienda()));
		
	}



}
