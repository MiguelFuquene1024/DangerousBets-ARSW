package edu.escuelaing.arsw.dangerousbet.security.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.escuelaing.arsw.dangerousbet.security.entity.EnSala;
import edu.escuelaing.arsw.dangerousbet.security.entity.Logros;
import edu.escuelaing.arsw.dangerousbet.security.entity.Salas;
import edu.escuelaing.arsw.dangerousbet.security.repository.EnSalaRepository;
import edu.escuelaing.arsw.dangerousbet.security.repository.MonedaRepository;

@Service
@Transactional
public class EnSalaService {
	
	
	@Autowired
	private EntityManager em;
	
	@Autowired
	EnSalaRepository salasRepository;
	
	
	public void save(EnSala sala){
		salasRepository.save(sala);
    }


	public int mayorSala() {
		try {
			TypedQuery<EnSala> query= em.createQuery("SELECT s FROM EnSala s ORDER BY id desc", EnSala.class);
			
			List<EnSala> resultList2 =query.getResultList();
	
			return resultList2.get(0).getId()+1;
		}catch(Exception e) {
			return 1;
		}
	}


	public boolean comprobar(String nombreSala, String contrasena) {
	
		TypedQuery<Salas> query= em.createQuery("SELECT s FROM Salas s WHERE nombre LIKE '" + nombreSala+"'",Salas.class);
		
		List<Salas> resultList =query.getResultList();
		if(resultList.size()==0) {
			return false;
		}
		else if(resultList.get(0).getClave()==null) {
			return true;
		}
		else if(resultList.get(0).getClave().equals(contrasena)) {
			return true;
		}
		
		return false;
	}

}
