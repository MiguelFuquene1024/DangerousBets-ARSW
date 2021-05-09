package edu.escuelaing.arsw.dangerousbet.security.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


public class Salas {
	
	
	private int valorsala;
	
	private  String nombre;
	

	private String clave;
	
	private boolean publico;
	
	private List<String> jugadores=new ArrayList<>();
	
	private boolean iniciada=false;
	
	
	public Salas() {
		
	}
	

	public Salas(int valorsala2,String nombre2, String clave2, boolean publico2) {
		nombre=nombre2;
		clave=clave2;
		publico=publico2;
		valorsala=valorsala2;

	}

	
	
	public List<String> getJugadores() {
		return jugadores;
	}


	public void setJugadores(List<String> jugadores) {
		this.jugadores = jugadores;
	}
	public void agregarJugador(String jugador) {
		if(!jugadores.contains(jugador)) {
			jugadores.add(jugador);
		}
	}
	public void eliminarJugador(String Jugador) {
		if(jugadores.contains(Jugador)) {
			jugadores.remove(Jugador);
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


	
	
	

}
