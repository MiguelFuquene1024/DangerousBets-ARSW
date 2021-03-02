package edu.escuelaing.arsw.dangerousbet.service;

import edu.escuelaing.arsw.dangerousbet.dao.UserDao;
import edu.escuelaing.arsw.dangerousbet.model.Usuario;
import edu.escuelaing.arsw.dangerousbet.util.EncrytedPasswodUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService{
    @Autowired
    UserDao dao;

    public boolean save(Usuario usuario){
        boolean correct=false;
        if(!dao.existsById(usuario.getNickname())) {
            correct=true;
            String pas= EncrytedPasswodUtils.encryted(usuario.getContrasena());
            usuario.setContrasena(pas);
            dao.save(usuario);
        }
        return correct;

    }


}
