package edu.escuelaing.arsw.dangerousbet.persistence;

import java.util.List;

import edu.escuelaing.arsw.dangerousbet.game.JuegoException;
import edu.escuelaing.arsw.dangerousbet.game.Player;
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

	public void comenzarJuego(String nameSala);


	public List<Player> obtenerPlayer(String sala, String name);

	public List<List<String>> obtenerMesa(String sala);

	public void pasarJugador(String sala) throws JuegoException;

	public void apostar(String sala, int apuesta) throws JuegoException;

	public void abandonarJuego(String sala);

}
