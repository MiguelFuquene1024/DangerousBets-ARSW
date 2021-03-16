package edu.escuelaing.arsw.dangerousbet.dao;

import edu.escuelaing.arsw.dangerousbet.model.Usuario;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface UserDao extends CrudRepository<Usuario, String> {

}
