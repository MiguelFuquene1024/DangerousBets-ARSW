package edu.escuelaing.arsw.dangerousbet.security.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="perfil")
public class Perfil {

	
	@Id
	private String nickname;
	

	private int moneda;
	
	private String imagen_perfil;


	public String getNickname() {
		return nickname;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	public int getMoneda() {
		return moneda;
	}


	public void setMoneda(int moneda) {
		this.moneda = moneda;
	}


	public String getImagen_perfil() {
		return imagen_perfil;
	}


	public void setImagen_perfil(String imagen_perfil) {
		this.imagen_perfil = imagen_perfil;
	}
	
	
}
