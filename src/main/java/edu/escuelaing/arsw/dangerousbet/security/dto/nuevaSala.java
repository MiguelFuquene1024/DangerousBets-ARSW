package edu.escuelaing.arsw.dangerousbet.security.dto;

public class nuevaSala {
	

	

	private  String nombre;
	
	private String clave;
	
	private int valorsala;
	
	public nuevaSala() {
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



}
