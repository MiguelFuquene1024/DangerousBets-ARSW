package edu.escuelaing.arsw.dangerousbet.security.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import edu.escuelaing.arsw.dangerousbet.security.entity.Perfil;
import edu.escuelaing.arsw.dangerousbet.security.entity.Tienda;
import edu.escuelaing.arsw.dangerousbet.security.entity.Usuario;
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

	public List<Tienda> logosComprados(String user) {
		System.out.println("=================================");
		return tienda.logosComprados(user);

	}

	public void actualizarDatos(Usuario us, String user) {
		Usuario u=usuario.getById(user).get();
		u.setCorreo(us.getCorreo());
		u.setName(us.getName());
		usuario.save(u);
	}
	
	public void actualizarDatosPerfil(Perfil pf, String user) {
		Perfil p=perfil.getPerfil(user);
		p.setImagen_perfil(pf.getImagen_perfil());
		perfil.save(p);
	}




}
