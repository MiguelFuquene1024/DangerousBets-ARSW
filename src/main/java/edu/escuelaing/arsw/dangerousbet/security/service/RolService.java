package edu.escuelaing.arsw.dangerousbet.security.service;

import edu.escuelaing.arsw.dangerousbet.security.entity.Rol;
import edu.escuelaing.arsw.dangerousbet.security.enums.RoleName;
import edu.escuelaing.arsw.dangerousbet.security.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class RolService {
    @Autowired
    RolRepository repository;

    public Optional<Rol> getByRolName(RoleName roleName){
        if(roleName.equals(RoleName.ROLE_USER)){
            return repository.findById(0);
        }
        else{
            return repository.findById(1);
        }
    }

    public Optional<Rol> getById(int val) {
        return repository.findById(val);
    }
}
