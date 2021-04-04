package edu.escuelaing.arsw.dangerousbet.security.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="usuario")
public class Usuario {

    @Column(name="nombre")
    private String name;

    private String contrasena;
    @Id
    private String nickname;
    @Column
    private String correo;
    @ManyToMany
    @JoinTable(name = "usuario_rol",joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "id"))
    private Set<Rol> roles = new HashSet<>();
    
    
    public Usuario() {
    }

    public Usuario(@NotNull(message = "Este espacio es obligatorio.") @Size(min = 5, max = 25, message = "El nombre debe tener al menos de 5 carecteres.") String name, @NotNull(message = "Este espacio es obligatorio.") @Size(min = 8, message = "La contaseña es demasiada débil.") String contrasena, @NotNull(message = "Este espacio es obligatorio.") @Size(min = 3, message = "No cumple con los requisitos.") String nickname, @NotNull(message = "Este espacio es obligatorio.") @Pattern(regexp = "[A-Z0-9a-z._%-]+@[A-Z0-9a-z._%-]+\\.[A-Za-z]+", message = "Este correo es invalido.") String correo) {
        this.name = name;
        this.contrasena = contrasena;
        this.nickname = nickname;
        this.correo = correo;
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

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }
}
