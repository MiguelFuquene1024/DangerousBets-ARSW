package edu.escuelaing.arsw.dangerousbet.security.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.escuelaing.arsw.dangerousbet.security.entity.Tienda;
import edu.escuelaing.arsw.dangerousbet.security.entity.UsuarioTienda;
import edu.escuelaing.arsw.dangerousbet.security.repository.LogrosRepository;

@Service
@Transactional
public class TiendaService {
	
	@Autowired
	LogrosRepository logrosRepository;

	@Autowired
	private EntityManager em;

	public List<Tienda> logosNoComprados(String user) {

		TypedQuery<Tienda> query= em.createQuery("SELECT t FROM Tienda t",Tienda.class);
		List<Tienda> tnda =query.getResultList();
		List<Tienda> Obtenidos = logosComprados(user);
		List<Tienda> noObtenidos = new ArrayList<Tienda>();
		for(Tienda t:tnda) {
			boolean f=true;
			for(Tienda tie:Obtenidos) {

				if(tie.equals(t)) {
					f=false;
				}
			}
			if(f) {
				noObtenidos.add(t);
			}
		}
		return noObtenidos;
	}
	public List<Tienda> logosComprados(String user){
		System.out.println("=================================");
		TypedQuery<Tienda> query= em.createQuery("SELECT t FROM UsuarioTienda ust JOIN Tienda t ON t.recurso=ust.tienda where usuario LIKE '" + user + "'",Tienda.class);
		System.out.println("=================================");
		List<Tienda> tnda =query.getResultList();
		System.out.println("=================================");
		return tnda;
	
	}

	
	
	public Tienda obtenerObjeto(String recurso) {
		TypedQuery<Tienda> query= em.createQuery("SELECT t FROM UsuarioTienda ust JOIN ust.tienda t where recurso LIKE '" + recurso + "'",Tienda.class);

		List<Tienda> tnda =query.getResultList();
		if (tnda.size()!=0){
			return tnda.get(0);
		}
		return null;
		
	}
	
}
