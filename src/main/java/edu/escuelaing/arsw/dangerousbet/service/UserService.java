package edu.escuelaing.arsw.dangerousbet.service;

import edu.escuelaing.arsw.dangerousbet.dao.RolDao;
import edu.escuelaing.arsw.dangerousbet.dao.UserDao;
import edu.escuelaing.arsw.dangerousbet.model.Rol;
import edu.escuelaing.arsw.dangerousbet.model.Usuario;
import edu.escuelaing.arsw.dangerousbet.util.EncrytedPasswodUtils;
import edu.escuelaing.arsw.dangerousbet.util.UserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService{
    @Autowired
    UserDao dao;
    @Autowired
    RolDao daoRol;

    public boolean save(Usuario usuario){
        boolean correct=false;
        if(!dao.existsById(usuario.getNickname())) {
            correct=true;
            String pas= EncrytedPasswodUtils.encryted(usuario.getContrasena());
            usuario.setContrasena(pas);
            Rol rol = new Rol();
            rol.setNickname(usuario.getNickname());
            rol.setValue("ROLE_USER");
            dao.save(usuario);
            daoRol.save(rol);

        }
        return correct;

    }
    public boolean login(Usuario usuario){
        boolean result=false;
        if(dao.existsById(usuario.getNickname())){
           Optional<Usuario> user= (dao.findById(usuario.getNickname()));
           String pass= user.get().getContrasena();
           result=(EncrytedPasswodUtils.matches(usuario.getContrasena(),pass));
        }
        return result;
    }
	public Integer getMonedas() {
		return 5000;
	}


}
