package edu.escuelaing.arsw.dangerousbet.security.service;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




import edu.escuelaing.arsw.dangerousbet.security.entity.Logros;

@Service
@Transactional
public class UsuarioLogrosService {



	
	@Autowired
	private EntityManager em;

	public  ArrayList<String[]> consultaPractica(String nombre){
		
		TypedQuery<Logros> cantLogros= em.createQuery("SELECT l FROM Logros l", Logros.class);
		TypedQuery<Logros> query= em.createQuery("SELECT l.logros_id FROM UsuarioLogros l WHERE l.nickname LIKE '" + nombre+"'",Logros.class);
		List<Logros> resultList2 =cantLogros.getResultList();
		List<Logros> resultList =query.getResultList();
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
		System.out.println(lista);
		return lista;
		
	}
	public  Integer[] consultaTrofeos(String nombre){
		TypedQuery<Logros> cantLogros= em.createQuery("SELECT l FROM Logros l", Logros.class);
		TypedQuery<Logros> query= em.createQuery("SELECT l.logros_id FROM UsuarioLogros l WHERE l.nickname LIKE '" + nombre+"'",Logros.class);
		List<Logros> resultList2 =cantLogros.getResultList();
		List<Logros> resultList =query.getResultList();
		Integer[] lista=new Integer[2];
		lista[0]=resultList.size();
		lista[1]=resultList2.size();
		
		return lista;
		
	}
	
}
