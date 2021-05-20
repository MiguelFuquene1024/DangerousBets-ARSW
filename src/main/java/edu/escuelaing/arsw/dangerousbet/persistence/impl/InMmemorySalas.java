package edu.escuelaing.arsw.dangerousbet.persistence.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import java.util.Timer; 
import java.util.TimerTask; 
import java.util.concurrent.ConcurrentHashMap;



import org.springframework.stereotype.Service;

import edu.escuelaing.arsw.dangerousbet.game.JuegoException;
import edu.escuelaing.arsw.dangerousbet.game.Player;
import edu.escuelaing.arsw.dangerousbet.game.impl.Poker;
import edu.escuelaing.arsw.dangerousbet.persistence.SalaPersistenceException;
import edu.escuelaing.arsw.dangerousbet.persistence.SalasPersistence;
import edu.escuelaing.arsw.dangerousbet.security.entity.Perfil;
import edu.escuelaing.arsw.dangerousbet.security.entity.Salas;




@Service
public class InMmemorySalas implements SalasPersistence{
	
	
	private final ConcurrentHashMap<String,Salas> salas=new ConcurrentHashMap<>();
	private final ConcurrentHashMap<String,Poker> juegos=new ConcurrentHashMap<>();
	
	
	public InMmemorySalas() {
		Salas s=new Salas(0,"Sala Publica",null,true);
		salas.put(s.getNombre(), s);
		cronoSalaPublica();
		
		
		
		
		
		TimerTask timerTask = new TimerTask() { 

            @Override 
            public void run() { 
            	for(Entry<String,Salas> entry:salas.entrySet()) {
					
					if(entry.getValue().getJugadores().size()==0) {
						eliminarSala(entry.getKey());
						if(salas.containsKey(entry.getKey())){
							juegos.remove(entry.getKey());
							
						}
					}
				}
            	
            } 
           }; 
   

		Timer timer = new Timer(); 
	    timer.schedule(timerTask,0,5000); 
	}
	private void cronoSalaPublica(){
		
	

           
		TimerTask timerTask = new TimerTask() { 

	            @Override 
	            public void run() { 
	            	
	            	eliminarSala("Sala Publica");
	            	cronoSalaPublica();
	            } 
	           }; 
	   

		Timer timer = new Timer(); 
		timer.schedule(timerTask,60000); 
	}
	
	public void agregarSala(Salas sala) throws SalaPersistenceException{
		if(!salas.containsKey(sala.getNombre())) {
			salas.put(sala.getNombre(), sala);
			TimerTask timerTask = new TimerTask() { 

	            @Override 
	            public void run() { 
	            	if(!salas.get(sala.getNombre()).isIniciada()) {
	            		eliminarSala(sala.getNombre());
	            	}
	            	
	            } 
	           }; 
	   

			Timer timer = new Timer(); 
		    timer.schedule(timerTask,600000); 
			
			
			
			
			
			
			
		}else {
			throw new SalaPersistenceException("Ya existe sala");
		}
	}
	
	public void agregarJugador(String sala,String clave,Perfil jugador) throws SalaPersistenceException {
	
		
		if(salas.containsKey(sala)) {

			if(salas.get(sala).getJugadores().size()<6 && !salas.get(sala).isIniciada() && (salas.get(sala).isPublico() || salas.get(sala).coincidenClaves(clave)) && jugador.getMoneda()>=salas.get(sala).getValorsala()) {

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
		if(juegos.containsKey(sala)) {
			juegos.get(sala).eliminarJugador(jugador);
			
		}
		
		
		
		
		
	}
	public String obtenerClaveDeAcceso(String jugador,String sala) {
		return salas.get(sala).obtenerClaveDeAcceso(jugador);
	}
	public ArrayList<List<String>> obtenerCartasJugador(String jugador,String sala) {
		return juegos.get(sala).obtenerCartasJugadores(jugador);
	}
	public void eliminarSala(String sala) {
		if(salas.containsKey(sala) && !salas.get(sala).getNombre().equals("Sala Publica")) {
			salas.remove(sala);
		}else if(salas.get(sala).getNombre().equals("Sala Publica")) {
			salas.get(sala).setIniciada(false);
			salas.get(sala).setJugadores(new ArrayList<>());
			salas.get(sala).setPublico(true);
			salas.get(sala).setRecompensaEntregada(false);
			
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
			if(entry.getValue().getJugadores().size()<6 && !entry.getValue().isIniciada() && entry.getValue().isPublico()) {
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

	@Override
	public void comenzarJuego(String nameSala) throws SalaPersistenceException {

		if(!salas.get(nameSala).isIniciada() && salas.get(nameSala).getJugadores().size()>1) {
			salas.get(nameSala).setIniciada(true);
			
			juegos.put(nameSala, new Poker());
			salas.get(nameSala).setIniciada(true);
			List<Integer> dinero=new ArrayList<>();
			for(int i=0;i<salas.get(nameSala).getJugadores().size();i++) {
				dinero.add(5000);
			}
			juegos.get(nameSala).iniciarPartida(salas.get(nameSala).getJugadores(), dinero);
			//juegos.get(nameSala).jugar();
		}else {
			throw new SalaPersistenceException("Debe haber minimo 3 jugadores para iniciar el juego");
		}
	}
	
	@Override
	public List<Player> obtenerPlayer(String sala,String name) {		
		
		sala=sala.replace("%20", " ");
		
		return juegos.get(sala).getJugadores();
	}
	
	@Override
	public Poker obtenerMesa(String sala){
		
		for(Player player:juegos.get(sala).getJugadores()) {
			if(player.isEliminado() || player.getTurnoSinJugar()==3) {
				eliminarJugador(sala,player.getNickName());
			}
		}
		return juegos.get(sala);
	}
	
	@Override
	public void pasarJugador(String sala,String user2) throws JuegoException{

		juegos.get(sala).pasar(user2);
	}
	@Override
	public void apostar(String sala,int apuesta,String user2) throws JuegoException{
		
		juegos.get(sala).apostar2(apuesta,user2);
	}
	@Override
	public void abandonarJuego(String sala,String user2) throws JuegoException{
		juegos.get(sala).abandonar(user2);
	}

	@Override
	public Salas getSalas(String nameSala) {
		Salas s=salas.get(nameSala);
		return s;
	}

	@Override
	public void nuevoMensaje(String sala, String mensaje) {
		salas.get(sala).nuevoMensajes(mensaje);
	}
	
	@Override
	public ArrayList recibirMensaje(String sala, String usuario) throws SalaPersistenceException {
		if(!salas.containsKey(sala)) throw new SalaPersistenceException("No existe la sala");
		return salas.get(sala).devolverMensajes(usuario);

	}
	
	
	
}
