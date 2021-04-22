package edu.escuelaing.arsw.dangerousbet.security.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.escuelaing.arsw.dangerousbet.security.entity.UsuarioTienda;
import edu.escuelaing.arsw.dangerousbet.security.repository.UsuarioTiendaRepository;

@Service
@Transactional
public class UsuarioTiendaService {
	
	@Autowired
	private EntityManager em;
	
	@Autowired
	UsuarioTiendaRepository usuarioTiendaRepository;


	public void save(UsuarioTienda usuarioTienda) throws serviceException{
		TypedQuery<String> query= em.createQuery("SELECT ust.tienda FROM UsuarioTienda ust WHERE ust.tienda LIKE '" + usuarioTienda.getTienda()+"' AND ust.usuario LIKE '"+usuarioTienda.getUsuario() +"'", String.class);
		if(query.getResultList().size()==0) {
			usuarioTiendaRepository.save(usuarioTienda);
		}else {
			throw new serviceException("Ya esta comprado");
		}
		
    }
	public int mayorIdUsuarioLogro(){

		TypedQuery<Integer> query= em.createQuery("SELECT ust.id FROM UsuarioTienda ust ORDER BY id desc", Integer.class);

		List<Integer> resultList2 =query.getResultList();
		
		return resultList2.get(0)+1;
    }
	
	

}
