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
	
	public int cantidadDejugadores(String nombreSala) {

			TypedQuery<EnSala> query2= em.createQuery("SELECT es FROM EnSala es WHERE nombre_sala LIKE '" + nombreSala +"'",EnSala.class);
			
			List<EnSala> resultList2 =query2.getResultList();
			System.out.println(resultList2.size()+" "+"####################################################");
			return resultList2.size();
	}


	public boolean comprobar(String nombreSala, String contrasena,String name) {
		System.out.println(nombreSala+" "+contrasena);
		TypedQuery<Salas> query= em.createQuery("SELECT s FROM Salas s WHERE nombre LIKE '" + nombreSala+"'",Salas.class);
		
		List<Salas> resultList =query.getResultList();
		TypedQuery<EnSala> query2= em.createQuery("SELECT es FROM EnSala es WHERE nickname LIKE '" + name +"'",EnSala.class);
		List<EnSala> resultList2 =query2.getResultList();
		try {
			if(resultList.size()==0 || resultList2.size()!=0){
				return false;
			}
			else if(resultList.get(0).isPublico() || resultList.get(0).getClave().equals(contrasena)) {
				return true;
			}
		}catch(Exception e) {}
		return false;
	}



}
