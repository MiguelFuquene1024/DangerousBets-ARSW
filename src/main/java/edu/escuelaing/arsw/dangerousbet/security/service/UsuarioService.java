package edu.escuelaing.arsw.dangerousbet.security.service;


import edu.escuelaing.arsw.dangerousbet.security.entity.Usuario;
import edu.escuelaing.arsw.dangerousbet.security.jwt.JwtProvider;
import edu.escuelaing.arsw.dangerousbet.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    JwtProvider jwtProvider;
    //No tocar
    public Optional<Usuario> getById(String user){

        return usuarioRepository.findById(user);
    }

    public Optional<Usuario> getByIdToken(String token){
        String user = jwtProvider.getNombreUsuarioFromToken(token);
        return usuarioRepository.findById(user);
    }

    //No tocar
    public boolean existsById(String nickName){
        return usuarioRepository.existsById(nickName);
    }

    //No tocar
    public void save(Usuario usuario){
  
        usuarioRepository.save(usuario);
    }

}
