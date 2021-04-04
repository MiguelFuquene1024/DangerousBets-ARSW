package edu.escuelaing.arsw.dangerousbet.security.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ensala")
public class EnSala {
	
	@Id
	private int id;
	
	
	private String nombre_sala;
	
	private String nickname;

	
	public EnSala() {
	}
	
	
	public EnSala(int mayorSala, String nickname2, String nombreSala) {
		id=mayorSala;
		nickname=nickname2;
		nombre_sala=nombreSala;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre_sala() {
		return nombre_sala;
	}

	public void setNombre_sala(String nombre_sala) {
		this.nombre_sala = nombre_sala;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	

}
