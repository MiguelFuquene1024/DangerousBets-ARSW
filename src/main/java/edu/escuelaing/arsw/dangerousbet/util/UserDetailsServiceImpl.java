 package edu.escuelaing.arsw.dangerousbet.util;

import edu.escuelaing.arsw.dangerousbet.dao.RolDao;
import edu.escuelaing.arsw.dangerousbet.dao.UserDao;
import edu.escuelaing.arsw.dangerousbet.model.Rol;
import edu.escuelaing.arsw.dangerousbet.model.Usuario;
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
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserDao dao;
    @Autowired
    RolDao rol;
    
  

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Usuario> user=dao.findById(s);
        Optional<Rol> rolUser= rol.findById(s);
        System.out.println(user.get().toString());
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        grantList.add(new SimpleGrantedAuthority(rolUser.get().getValue()));
        UserDetails userDetails = (UserDetails) new User(user.get().getNickname(),user.get().getContrasena(), grantList);
        return userDetails;

    }
}
