package edu.escuelaing.arsw.dangerousbet.model;




import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;




@Entity
@Table(name="usuario")
public class Usuario {
	@NotNull(message="Este espacio es obligatorio.")
	@Column(name="nombre")
	@Size(min = 5,max=25, message="El nombre debe tener al menos de 5 carecteres.")
	private String name;
	
	
	@NotNull(message="Este espacio es obligatorio.")
	@Size(min = 8, message="La contaseña es demasiada débil.")
	private String contrasena;
	
	@Id
	@NotNull(message="Este espacio es obligatorio.")
	@Size(min = 3, message="No cumple con los requisitos.")
	private String nickname;
	
	@Column
	@NotNull(message="Este espacio es obligatorio.")
	@Pattern(regexp="[A-Z0-9a-z._%-]+@[A-Z0-9a-z._%-]+\\.[A-Za-z]+",message="Este correo es invalido." )
	private String correo;

	public Usuario() {
		
	}
	public Usuario(String nombre, String password, String email,String nickname) {
		
        this.name = nombre;
        this.contrasena = password;
        this.correo = email;
        this.nickname=nickname;
    }
    public Usuario(String nickname){
		this.nickname=nickname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public String getNickname() {
		return nickname;
	}

	@Override
	public String toString() {
		return "Usuario{" +
				"name='" + name + '\'' +
				", contrasena='" + contrasena + '\'' +
				", nickname='" + nickname + '\'' +
				", correo='" + correo + '\'' +
				'}';
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
}
