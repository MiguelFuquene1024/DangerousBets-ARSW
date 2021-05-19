package edu.escuelaing.arsw.dangerousbet.security.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import edu.escuelaing.arsw.dangerousbet.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.escuelaing.arsw.dangerousbet.security.entity.Perfil;
import edu.escuelaing.arsw.dangerousbet.security.entity.Salas;
import edu.escuelaing.arsw.dangerousbet.security.repository.PerfilRepository;

@Service
@Transactional
public class PerfilService {

	@Autowired
	private EntityManager em;
	
	@Autowired
	PerfilRepository perfilRepository;

	@Autowired
	JwtProvider jwtProvider;
	
	public  String[][] getMejoresPosiciones(){
		TypedQuery<Perfil> query= em.createQuery("SELECT d FROM Perfil d ORDER BY moneda desc", Perfil.class);
		
		List<Perfil> resultList2 =query.getResultList();
		String[][] lista=new String[10][2];
		for(int i=0;i<10;i++) {
				String[] s=new String[2];
				s[0]=resultList2.get(i).getNickname();
				s[1]=resultList2.get(i).getMoneda()+"";
				lista[i]=s;
			
		}
		
		
		return lista;
		
	}
	
	public  Perfil getPerfil(String name){

		Perfil perfil=perfilRepository.findById(name).get();
		
		return perfil;
		
	}

	public void save(Perfil p) {
		perfilRepository.save(p);
	}

	public void restarDineroDejuego(Salas sala) {
		for(String nick:sala.getJugadores()) {
			Perfil perfil=perfilRepository.findById(nick).get();
			perfil.setMoneda(perfil.getMoneda()-sala.getValorsala());
			save(perfil);
		}
		
	}

	public void sumarRecompensa(Salas s, int i) {
		Perfil perfil=perfilRepository.findById(s.getJugadores().get(0)).get();
		perfil.setMoneda(perfil.getMoneda()+(s.getValorsala()*i));
		save(perfil);
		
	}
}
