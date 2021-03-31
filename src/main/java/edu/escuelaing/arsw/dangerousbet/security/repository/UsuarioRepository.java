package edu.escuelaing.arsw.dangerousbet.security.repository;

import edu.escuelaing.arsw.dangerousbet.security.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    }
