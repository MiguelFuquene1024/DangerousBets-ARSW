package edu.escuelaing.arsw.dangerousbet.service;

import edu.escuelaing.arsw.dangerousbet.dao.RolDao;
import edu.escuelaing.arsw.dangerousbet.model.Rol;
import org.springframework.beans.factory.annotation.Autowired;

public class RolService {
    @Autowired
    RolDao dao;

    public boolean save(Rol rol){
        boolean correcto=false;
        if(!dao.existsById(rol.getNickname())){
            correcto=true;
            save(rol);
        }
        return correcto;
    }
}
