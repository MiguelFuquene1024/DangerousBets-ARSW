package edu.escuelaing.arsw.dangerousbet.security.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.escuelaing.arsw.dangerousbet.security.entity.Dinero;
import edu.escuelaing.arsw.dangerousbet.security.repository.MonedaRepository;

@Service
@Transactional
public class MonedaService {

	@Autowired
	private EntityManager em;
	
	@Autowired
	MonedaRepository monedaRepository;
	
	public  String[][] getMejoresPosiciones(){
		TypedQuery<Dinero> query= em.createQuery("SELECT d FROM Dinero d ORDER BY moneda desc", Dinero.class);
		
		List<Dinero> resultList2 =query.getResultList();
		String[][] lista=new String[10][2];
		for(int i=0;i<10;i++) {
				String[] s=new String[2];
				s[0]=resultList2.get(i).getNickname();
				s[1]=resultList2.get(i).getMoneda()+"";
				lista[i]=s;
			
		}
		
		
		return lista;
		
	}
	
	public  int getMonedas(String name){
		
		Dinero dinero=monedaRepository.findById(name).get();
		
		return dinero.getMoneda();
		
	}
}
