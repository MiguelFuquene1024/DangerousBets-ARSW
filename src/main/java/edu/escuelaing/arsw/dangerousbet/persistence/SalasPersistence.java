package edu.escuelaing.arsw.dangerousbet.persistence;

import java.util.ArrayList;
import java.util.List;

import edu.escuelaing.arsw.dangerousbet.game.JuegoException;
import edu.escuelaing.arsw.dangerousbet.game.Player;
import edu.escuelaing.arsw.dangerousbet.game.impl.Poker;
import edu.escuelaing.arsw.dangerousbet.security.entity.Perfil;
import edu.escuelaing.arsw.dangerousbet.security.entity.Salas;
import edu.escuelaing.arsw.dangerousbet.security.entity.Usuario;


public interface SalasPersistence {
	
	
	public void agregarSala(Salas sala) throws SalaPersistenceException;
	
	public void agregarJugador(String sala,String clave,Perfil jugador) throws SalaPersistenceException;
	
	
	public void eliminarJugador(String sala,String jugador);
	
	public void eliminarSala(String sala);

	public Salas obtenerSala(String s2);

	public List<Salas> getSalasPublicas();

	public void privacidadSala(String sala);

	public void comenzarJuego(String nameSala) throws SalaPersistenceException;


	public List<Player> obtenerPlayer(String sala, String name);

	public Poker obtenerMesa(String sala);

	public void pasarJugador(String sala, String user2) throws JuegoException;

	public void apostar(String sala, int apuesta,String user2) throws JuegoException;

	public void abandonarJuego(String sala,String user2) throws JuegoException;

	public Salas getSalas(String nameSala);
	
	public void nuevoMensaje(String sala,String mensaje);

	public ArrayList recibirMensaje(String sala, String usuario) throws SalaPersistenceException;
	public String obtenerClaveDeAcceso(String jugador,String sala);
	public ArrayList<List<String>> obtenerCartasJugador(String jugador,String sala);
}
