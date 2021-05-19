package edu.escuelaing.arsw.dangerousbet.security.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import edu.escuelaing.arsw.dangerousbet.game.JuegoException;
import edu.escuelaing.arsw.dangerousbet.game.Player;
import edu.escuelaing.arsw.dangerousbet.game.impl.Poker;
import edu.escuelaing.arsw.dangerousbet.persistence.SalaPersistenceException;
import edu.escuelaing.arsw.dangerousbet.persistence.SalasPersistence;
import edu.escuelaing.arsw.dangerousbet.security.entity.Perfil;
import edu.escuelaing.arsw.dangerousbet.security.entity.Salas;
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
	private SalasPersistence slp;
	
	

	public void comprarLogo(UsuarioTienda ut) throws serviceException{

		int numero = ust.mayorIdUsuarioLogro();
		ust.save(new UsuarioTienda(numero,ut.getUsuario(),ut.getTienda()));
		
	}

	public List<Tienda> logosComprados(String user) {
		
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

	public void crearSala(Salas sala2) throws SalaPersistenceException{
			
			slp.agregarSala(sala2);
		
		
	}

	public void agregarJugador(String sala,String clave ,String nj) throws SalaPersistenceException{
		Perfil us=perfil.getPerfil(nj);
		slp.agregarJugador(sala, clave,us);
		
	}

	public Salas obtenerSala(String s2) {
		
		return slp.obtenerSala(s2);
	}

	public List<Salas> getSalasPublicas() {

		return slp.getSalasPublicas();
	}

	public void privacidadSala(String sala) {
		slp.privacidadSala(sala);
		
	}

	public void eliminarJugador(String sala, String nj) {
		slp.eliminarJugador(sala,nj);
		
	}

	public void comenzarJuego(String nameSala) throws SalaPersistenceException {
		
		nameSala=nameSala.replace("%20", " ");

		
		slp.comenzarJuego(nameSala);
		perfil.restarDineroDejuego(slp.getSalas(nameSala));
	}
	
	public List<Player> obtenerPlayer(String sala, String nj) {
		return slp.obtenerPlayer(sala, nj);
	}

	public Poker obtenerMesa(String sala){
		Poker poker=slp.obtenerMesa(sala);
		Salas s=obtenerSala(sala);
		if(s.getJugadores().size()==1 && s.isIniciada() && !s.isRecompensaEntregada()) {
			s.setRecompensaEntregada(true);
			perfil.sumarRecompensa(s,poker.getJugadores().size());
		}
		return poker;
		
	}
	
	public void pasarJugador(String sala) throws JuegoException{
		slp.pasarJugador(sala);;
		
	}
	public void apostar(String sala,int apuesta) throws JuegoException{
		slp.apostar(sala, apuesta);;
		
	}
	public void abandonarJuego(String sala){
		slp.abandonarJuego(sala);
		
	}
	public void nuevoMensaje(String sala,String mensaje) {
		slp.nuevoMensaje(sala, mensaje);
	}
	
	public ArrayList recibirMensaje(String sala,String usuario) {
		return slp.recibirMensaje(sala, usuario);
	}

}
