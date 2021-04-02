package edu.escuelaing.arsw.dangerousbet.security.service;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;



import edu.escuelaing.arsw.dangerousbet.security.entity.Logros;
import edu.escuelaing.arsw.dangerousbet.security.entity.Usuario;
import edu.escuelaing.arsw.dangerousbet.security.entity.UsuarioLogros;

@Service
@Transactional
public class UsuarioLogrosService {


	private Object entityManager;
	
	@Autowired
	private EntityManager em;

	public  ArrayList<String[]> consultaPractica(){
		System.out.println("#########################################");
		CriteriaBuilder cb = em.getCriteriaBuilder();
		//TypedQuery<Logros> query= em.createQuery("SELECT l FROM UsuarioLogros ul JOIN Logros l ON ul.logro=l.id WHERE ul.nickname LIKE 'yarit'",Logros.class);
		TypedQuery<Logros> cantLogros= em.createQuery("SELECT l FROM Logros l", Logros.class);
		
		
		TypedQuery<Logros> query= em.createQuery("SELECT l.logros_id FROM UsuarioLogros l WHERE l.nickname LIKE 'yarit'",Logros.class);
		
		
		List<Logros> resultList2 =cantLogros.getResultList();
		System.out.println("#########################################");
		
		List<Logros> resultList =query.getResultList();
		System.out.println("#########################################");
		
		
		 ArrayList<String[]> lista=new ArrayList<>();
		for(int i=0;i<resultList2.size();i++) {
			String[] tr=new String[2];
			tr[0]=null;
			tr[1]=resultList2.get(i).getNombre();
			lista.add(tr);
		}
		
		
		for(int i=0;i<resultList.size();i++) {
			String[] tr2=lista.get(resultList.get(i).getId()-1);
			tr2[0]=resultList.get(i).getRecurso();
			lista.set(resultList.get(i).getId()-1, tr2);	
		}
		
		return lista;
		
	}
}
