package edu.escuelaing.arsw.dangerousbet.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.escuelaing.arsw.dangerousbet.security.entity.Dinero;

public interface MonedaRepository extends JpaRepository <Dinero,String>{

}
