package edu.escuelaing.arsw.dangerousbet.security.entity;

import edu.escuelaing.arsw.dangerousbet.security.enums.RoleName;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private RoleName rolNombre;

    public Rol() {
    }

    public Rol(@NotNull RoleName rolNombre) {
        this.rolNombre = rolNombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RoleName getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(RoleName rolNombre) {
        this.rolNombre = rolNombre;
    }
}
