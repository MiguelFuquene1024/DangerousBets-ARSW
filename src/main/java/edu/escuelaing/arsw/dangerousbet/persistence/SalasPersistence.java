package edu.escuelaing.arsw.dangerousbet.persistence;

import java.util.List;

import edu.escuelaing.arsw.dangerousbet.security.entity.Salas;


public interface SalasPersistence {
	
	
	public void agregarSala(Salas sala);
	
	public void agregarJugador(String sala,String clave,String jugador);
	
	
	public void eliminarJugador(String sala,String jugador);
	
	public void eliminarSala(String sala);

	public Salas obtenerSala(String s2);

	public List<Salas> getSalasPublicas();

	public void privacidadSala(String sala);

}
