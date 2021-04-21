package edu.escuelaing.arsw.dangerousbet.persistence.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;



import org.springframework.stereotype.Service;

import edu.escuelaing.arsw.dangerousbet.persistence.SalasPersistence;
import edu.escuelaing.arsw.dangerousbet.security.entity.Salas;



@Service
public class InMmemorySalas implements SalasPersistence{
	

	private final ConcurrentHashMap<String,Salas> salas=new ConcurrentHashMap<>();
	
	public InMmemorySalas() {
		Salas s=new Salas(0,"Sala Publica",null,true);
		salas.put(s.getNombre(), s);

	}
	
	public void agregarSala(Salas sala) {
		salas.put(sala.getNombre(), sala);
	}
	
	public void agregarJugador(String sala,String clave,String jugador) {
	
		
		if(salas.containsKey(sala)) {

			if(salas.get(sala).isPublico() || salas.get(sala).getClave().equals(clave)) {

				salas.get(sala).agregarJugador(jugador);

			}
		
		}
	}
	
	public void eliminarJugador(String sala,String jugador) {
		if(salas.containsKey(sala)) {
			salas.get(sala).eliminarJugador(jugador);
		}
	}
	
	public void eliminarSala(String sala) {
		if(salas.containsKey(sala)) {
			salas.remove(sala);
		}
	}

	@Override
	public Salas obtenerSala(String s2) {
		if(salas.containsKey(s2)) {
			return salas.get(s2);
		}
		return null;
	}

	@Override
	public List<Salas> getSalasPublicas() {
		List<Salas> sp=new ArrayList<Salas>();
		for(Entry<String,Salas> entry:salas.entrySet()) {
			if(entry.getValue().isPublico()) {
				sp.add(entry.getValue());
			}
		}
		return sp;
	}

	@Override
	public void privacidadSala(String sala) {
		if(salas.containsKey(sala)) {
			salas.get(sala).setPublico(!salas.get(sala).isPublico());
		}
		
	}
	
	
}
