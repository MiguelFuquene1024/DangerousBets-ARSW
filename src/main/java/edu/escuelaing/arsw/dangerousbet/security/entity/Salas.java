package edu.escuelaing.arsw.dangerousbet.security.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


public class Salas {
	
	
	private int valorsala;
	
	private HashMap<String,ArrayList<String>> chat;
	
	private HashMap<String,Integer> numeroChat;
	
	private  String nombre;
	
	private boolean recompensaEntregada=false;
	
	private String clave;
	
	private boolean publico;
	
	private List<String> jugadores=new ArrayList<>();
	
	
	private boolean iniciada=false;
	
	
	public Salas() {
		chat=new HashMap<>();
		numeroChat=new HashMap<>();
	}
	

	public Salas(int valorsala2,String nombre2, String clave2, boolean publico2) {
		nombre=nombre2;
		clave=clave2;
		publico=publico2;
		valorsala=valorsala2;
		chat=new HashMap<>();
		numeroChat=new HashMap<>();
	}

	
	
	public List<String> getJugadores() {
		return jugadores;
	}


	public void setJugadores(List<String> jugadores) {
		this.jugadores = jugadores;
		for(String s:this.jugadores) {
			chat.put(s, new ArrayList<>());
			numeroChat.put(s,0 );
		}
		
	}
	public void agregarJugador(String jugador) {
		if(!jugadores.contains(jugador)) {
			jugadores.add(jugador);
			chat.put(jugador, new ArrayList<>());
			numeroChat.put(jugador,0 );
		}
	}
	public void eliminarJugador(String Jugador) {
		if(jugadores.contains(Jugador)) {
			jugadores.remove(Jugador);
			chat.remove(Jugador);
			numeroChat.remove(Jugador);
		}
	}


	public int getValorsala() {
		return valorsala;
	}

	public void setValorsala(int valorsala) {
		this.valorsala = valorsala;
	}



	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

	

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public boolean isPublico() {
		return publico;
	}

	public void setPublico(boolean publico) {
		this.publico = publico;
	}


	public boolean isIniciada() {
		return iniciada;
	}


	public void setIniciada(boolean iniciada) {
		this.iniciada = iniciada;
	}

	public void nuevoMensajes(String mensaje) {
		for(String jugador:jugadores) {
			chat.get(jugador).add(mensaje);
		}
	}
	
	public ArrayList<String> devolverMensajes(String jugador) {
		ArrayList<String> msg=new ArrayList<>(); 
		
		for(int i=numeroChat.get(jugador);i<chat.get(jugador).size();i++) {
			
			msg.add(chat.get(jugador).get(i));
		}
		numeroChat.put(jugador,chat.get(jugador).size());
		return msg;
		
	}


	public boolean isRecompensaEntregada() {
		return recompensaEntregada;
	}


	public void setRecompensaEntregada(boolean recompensaEntregada) {
		this.recompensaEntregada = recompensaEntregada;
	}
	
	
	
	
	

}
