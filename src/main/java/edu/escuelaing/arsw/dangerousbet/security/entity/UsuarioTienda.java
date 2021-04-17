package edu.escuelaing.arsw.dangerousbet.security.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="usuario_tienda")
public class UsuarioTienda {
	
	@Id
	private int id;
	
	private String usuario;
	

	private String tienda;

	public UsuarioTienda(int numero, String user, String recurso) {
		id=numero;
		usuario=user;
		tienda=recurso;
	}
	public UsuarioTienda() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getTienda() {
		return tienda;
	}

	public void setTienda(String tienda ) {
		this.tienda = tienda;
	}
	
	

}
