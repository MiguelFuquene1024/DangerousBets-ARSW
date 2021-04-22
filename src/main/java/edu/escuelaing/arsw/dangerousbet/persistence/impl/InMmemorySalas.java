package edu.escuelaing.arsw.dangerousbet.persistence.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.util.concurrent.ConcurrentHashMap;



import org.springframework.stereotype.Service;

import edu.escuelaing.arsw.dangerousbet.persistence.SalaPersistenceException;
import edu.escuelaing.arsw.dangerousbet.persistence.SalasPersistence;
import edu.escuelaing.arsw.dangerousbet.security.entity.Perfil;
import edu.escuelaing.arsw.dangerousbet.security.entity.Salas;




@Service
public class InMmemorySalas implements SalasPersistence{
	

	private final ConcurrentHashMap<String,Salas> salas=new ConcurrentHashMap<>();
	
	public InMmemorySalas() {
		Salas s=new Salas(0,"Sala Publica",null,true);
		salas.put(s.getNombre(), s);
		Timer timer=new Timer(5000,new ActionListener() {


			@Override
			public void actionPerformed(ActionEvent e) {
				for(Entry<String,Salas> entry:salas.entrySet()) {
					
					if(entry.getValue().getJugadores().size()==0) {
						eliminarSala(entry.getKey());
					}
				}
				
			}
		});
		
		timer.start();

	}
	
	public void agregarSala(Salas sala) throws SalaPersistenceException{
		if(!salas.containsKey(sala.getNombre())) {
			salas.put(sala.getNombre(), sala);
		}else {
			throw new SalaPersistenceException("Ya existe sala");
		}
	}
	
	public void agregarJugador(String sala,String clave,Perfil jugador) throws SalaPersistenceException {
	
		
		if(salas.containsKey(sala)) {

			if((salas.get(sala).isPublico() || salas.get(sala).getClave().equals(clave)) && jugador.getMoneda()>=salas.get(sala).getValorsala()) {

				salas.get(sala).agregarJugador(jugador.getNickname());

			}else {
				throw new SalaPersistenceException("No puede ingresar a la sala");
			}
		}else {
			throw new SalaPersistenceException("No existe esta sala");
		}
	}
	
	public void eliminarJugador(String sala,String jugador) {
		if(salas.containsKey(sala)) {
			salas.get(sala).eliminarJugador(jugador);
		}
	}
	
	public void eliminarSala(String sala) {
		if(salas.containsKey(sala) && !salas.get(sala).getNombre().equals("Sala Publica")) {
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
