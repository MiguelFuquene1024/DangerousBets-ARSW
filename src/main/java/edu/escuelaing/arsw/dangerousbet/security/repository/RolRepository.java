package edu.escuelaing.arsw.dangerousbet.security.repository;

import edu.escuelaing.arsw.dangerousbet.security.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RolRepository extends JpaRepository<Rol,Integer> {


}
