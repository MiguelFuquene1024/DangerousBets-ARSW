package edu.escuelaing.arsw.dangerousbet.security.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="salas")
public class Salas {
	
	@Id
	private int id;
	
	private int valorsala;
	
	private  String nombre;
	
	private String clave;
	
	private boolean publico;

	public Salas() {
		
	}
	
	public Salas(int id2, int valorsala2,String nombre2, String clave2, boolean publico2) {
		id=id2;
		nombre=nombre2;
		clave=clave2;
		publico=publico2;
		valorsala=valorsala2;
	}

	public int getId() {
		return id;
	}
	
	public int getValorsala() {
		return valorsala;
	}

	public void setValorsala(int valorsala) {
		this.valorsala = valorsala;
	}

	public void setId(int id) {
		this.id = id;
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
	
	

}
