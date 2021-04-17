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


	public void save(UsuarioTienda usuarioTienda){
		System.out.println(usuarioTienda.getId());
		usuarioTiendaRepository.save(usuarioTienda);
    }
	public int mayorIdUsuarioLogro(){
		System.out.println("hola3");
		TypedQuery<Integer> query= em.createQuery("SELECT ust.id FROM UsuarioTienda ust ORDER BY id desc", Integer.class);

		List<Integer> resultList2 =query.getResultList();
		System.out.println("hola5");
		return resultList2.get(0)+1;
    }
	
	

}
