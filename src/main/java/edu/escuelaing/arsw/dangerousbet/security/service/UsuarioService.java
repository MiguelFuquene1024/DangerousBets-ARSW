package edu.escuelaing.arsw.dangerousbet.security.service;


import edu.escuelaing.arsw.dangerousbet.security.entity.Usuario;
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

    public Optional<Usuario> getById(String nickName){
        return usuarioRepository.findById(nickName);
    }

    public boolean existsById(String nickName){
        return usuarioRepository.existsById(nickName);
    }

    public void save(Usuario usuario){
        usuarioRepository.save(usuario);
    }
  
}
