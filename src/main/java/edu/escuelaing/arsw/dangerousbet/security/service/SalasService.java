package edu.escuelaing.arsw.dangerousbet.security.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.escuelaing.arsw.dangerousbet.security.entity.Dinero;
import edu.escuelaing.arsw.dangerousbet.security.entity.Salas;
import edu.escuelaing.arsw.dangerousbet.security.repository.SalasRepository;

@Service
@Transactional
public class SalasService {
	
	@Autowired
	private EntityManager em;
	
	@Autowired
	SalasRepository salasRepository;
	
	public void save(Salas sala){
		salasRepository.save(sala);
    }
	
	
	public int mayorSala(){
		TypedQuery<Salas> query= em.createQuery("SELECT s FROM Salas s ORDER BY id desc", Salas.class);
		
		List<Salas> resultList2 =query.getResultList();

		return resultList2.get(0).getId()+1;
    }

}
