package edu.escuelaing.arsw.dangerousbet.security.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="dinero")
public class Dinero {

	
	@Id
	private String nickname;
	

	private int moneda;


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
	
	
}
