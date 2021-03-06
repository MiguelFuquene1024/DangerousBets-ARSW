package edu.escuelaing.arsw.dangerousbet.security.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import edu.escuelaing.arsw.dangerousbet.security.jwt.JwtProvider;
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
	private UsuarioLogrosService ul;
	
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

	@Autowired
	JwtProvider jwtProvider;
	
	

	public void comprarLogo(UsuarioTienda ut) throws serviceException{
		String user = jwtProvider.getNombreUsuarioFromToken(ut.getUsuario());
		int numero = ust.mayorIdUsuarioLogro();
		ust.save(new UsuarioTienda(numero,user,ut.getTienda()));
		
	}

	public List<Tienda> logosComprados(String token) {
		String user = jwtProvider.getNombreUsuarioFromToken(token);
		return tienda.logosComprados(user);

	}

	public void actualizarDatos(Usuario us, String token) {
		String user = jwtProvider.getNombreUsuarioFromToken(token);
		Usuario u=usuario.getById(user).get();
		u.setCorreo(us.getCorreo());
		u.setName(us.getName());
		usuario.save(u);
	}
	
	public void actualizarDatosPerfil(Perfil pf, String token) {
		String user = jwtProvider.getNombreUsuarioFromToken(token);
		Perfil p=perfil.getPerfil(user);
		p.setImagen_perfil(pf.getImagen_perfil());
		perfil.save(p);
	}
	public ArrayList<List<String>> obtenerCartasJugador(String token,String sala){

		String jugador = jwtProvider.getNombreUsuarioFromToken(token);

		return slp.obtenerCartasJugador(jugador, sala);
	}
	public void crearSala(Salas sala2) throws SalaPersistenceException{
			slp.agregarSala(sala2);
	}

	public void agregarJugador(String sala,String clave ,String nj) throws SalaPersistenceException{
		String user = jwtProvider.getNombreUsuarioFromToken(nj);

		Perfil us=perfil.getPerfil(user);
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
		String user = jwtProvider.getNombreUsuarioFromToken(nj);
		slp.eliminarJugador(sala,user);
		
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
	
	public void pasarJugador(String sala, String user) throws JuegoException{
		String user2 = jwtProvider.getNombreUsuarioFromToken(user);
		slp.pasarJugador(sala,user2);
		
	}
	public void apostar(String sala,int apuesta,String user) throws JuegoException{
		String user2 = jwtProvider.getNombreUsuarioFromToken(user);
		slp.apostar(sala, apuesta,user2);
		
	}
	public void abandonarJuego(String sala,String user) throws JuegoException{
		String user2 = jwtProvider.getNombreUsuarioFromToken(user);
		slp.abandonarJuego(sala,user2);
		
	}
	public void nuevoMensaje(String sala,String mensaje) {
		slp.nuevoMensaje(sala, mensaje);
	}

	public ArrayList recibirMensaje(String sala,String usuario) throws SalaPersistenceException {
		return slp.recibirMensaje(sala, jwtProvider.getNombreUsuarioFromToken(usuario));
	}
	public String obtenerClaveDeAcceso(String jugador,String sala) {
		
		return slp.obtenerClaveDeAcceso(jwtProvider.getNombreUsuarioFromToken(jugador), sala);
	}

}
