package edu.escuelaing.arsw.dangerousbet.security.service;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import edu.escuelaing.arsw.dangerousbet.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.escuelaing.arsw.dangerousbet.game.impl.Poker;
import edu.escuelaing.arsw.dangerousbet.security.entity.Logros;
import edu.escuelaing.arsw.dangerousbet.security.entity.UsuarioLogros;
import edu.escuelaing.arsw.dangerousbet.security.repository.UsuarioLogrosRepository;

@Service
@Transactional
public class UsuarioLogrosService {

	
	@Autowired
	private UsuarioLogrosRepository usuarioLogrosRepository;
	
	@Autowired
	private EntityManager em;

	@Autowired
	JwtProvider jwtProvider;


	public  ArrayList<String[]> consultaPractica(String token){

		String nombre = jwtProvider.getNombreUsuarioFromToken(token);
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

		return lista;
		
	}
	public  Integer[] consultaTrofeos(String token){

		String nombre = jwtProvider.getNombreUsuarioFromToken(token);
		TypedQuery<Logros> cantLogros= em.createQuery("SELECT l FROM Logros l", Logros.class);
		TypedQuery<Logros> query= em.createQuery("SELECT l.logros_id FROM UsuarioLogros l WHERE l.nickname LIKE '" + nombre+"'",Logros.class);
		List<Logros> resultList2 =cantLogros.getResultList();
		List<Logros> resultList =query.getResultList();
		Integer[] lista=new Integer[2];
		lista[0]=resultList.size();
		lista[1]=resultList2.size();
		
		return lista;
		
	}
	public int idMayor() {
		TypedQuery<Integer> query= em.createQuery("SELECT usl.id FROM UsuarioLogros usl ORDER BY id desc", Integer.class);

		List<Integer> resultList2 =query.getResultList();
		
		return resultList2.get(0)+1;

	}
	public void darTrofeos(Poker poker) {
		System.out.println("#################################################################################");
		TypedQuery<Logros> cantLogros= em.createQuery("SELECT l FROM Logros l", Logros.class);
		TypedQuery<Logros> query= em.createQuery("SELECT l.logros_id FROM UsuarioLogros l WHERE l.nickname LIKE '" +poker.getGanador() +"'",Logros.class);
		List<Logros> resultList2 =cantLogros.getResultList();
		List<Logros> resultList =query.getResultList();
		HashMap<String, Logros> dicc=new HashMap<>();
		HashMap<String, Logros> dicc2=new HashMap<>();
		for(Logros logros:resultList2) {
			dicc.put(logros.getNombre(), logros);
		}
		for(Logros logros:resultList) {
			dicc2.put(logros.getNombre(), logros);
		}
		System.out.println(poker.getFormaVictoria() + "aaaa");
		
		if(poker.getFormaVictoria().equals("full")  && !dicc2.containsKey("Gana por Full-House")) {
			System.out.println(1);
			UsuarioLogros ul=new UsuarioLogros(idMayor(),poker.getGanador(),dicc.get("Gana por Full-House"));
			usuarioLogrosRepository.save(ul);
		}if(poker.getFormaVictoria().equals("escalera") && !dicc2.containsKey("Gana por escalera")) {
			System.out.println(2);
			UsuarioLogros ul=new UsuarioLogros(idMayor(),poker.getGanador(),dicc.get("Gana por escalera"));
			usuarioLogrosRepository.save(ul);
		}if(poker.getFormaVictoria().equals("colorCartas") && !dicc2.containsKey("Gana por Color")) {
			System.out.println(3);
			UsuarioLogros ul=new UsuarioLogros(idMayor(),poker.getGanador(),dicc.get("Gana por Color"));
			usuarioLogrosRepository.save(ul);
		}if(poker.getJugador(poker.getGanador()).getVictoriasConsecutivas()==3 && !dicc2.containsKey("Gana 3 rondas consecutivas")) {
			System.out.println(4);
			UsuarioLogros ul=new UsuarioLogros(idMayor(),poker.getGanador(),dicc.get("Gana 3 rondas consecutivas"));
			usuarioLogrosRepository.save(ul);
		}if(poker.getEstadoPartida().equals("finJuego") && !dicc2.containsKey("Gana el juego")) {
			System.out.println(5);
			UsuarioLogros ul=new UsuarioLogros(idMayor(),poker.getGanador(),dicc.get("Gana el juego"));
			usuarioLogrosRepository.save(ul);
		}if(poker.getEstadoPartida().equals("abandono") && !dicc2.containsKey("Gana porque todos se retiran")) {
			System.out.println(6);
			UsuarioLogros ul=new UsuarioLogros(idMayor(),poker.getGanador(),dicc.get("Gana porque todos se retiran"));
			usuarioLogrosRepository.save(ul);
		}if(poker.getNumeroDeJuegos()==1 && !dicc2.containsKey("Gana la primera ronda")) {
			System.out.println(7);
			UsuarioLogros ul=new UsuarioLogros(idMayor(),poker.getGanador(),dicc.get("Gana la primera ronda"));
			usuarioLogrosRepository.save(ul);
		}if(true) {
			
		}
	}
	
	
}
