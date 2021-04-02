package edu.escuelaing.arsw.dangerousbet.security.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="usuarios_logros")
public class UsuarioLogros {
	
	//@OneToMany(mappedBy = "nickname")
	private String nickname;
	
	@ManyToOne
	@JoinColumn(name="logros_id")
	private Logros logros_id;
	
	
	@Id
	private int id;
	

	public Logros getLogros_id() {
		return logros_id;
	}

	public void setLogros_id(Logros logros_id) {
		this.logros_id = logros_id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}




}
