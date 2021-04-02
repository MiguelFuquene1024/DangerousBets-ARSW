package edu.escuelaing.arsw.dangerousbet.security.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.escuelaing.arsw.dangerousbet.security.repository.LogrosRepository;

@Service
@Transactional
public class LogrosService {
	
	@Autowired
	LogrosRepository logrosRepository;
	
	public void getLogros(){
	//	@Query(value = "select ul.logro ,l.recurso \r\n" + 
		//		"from usuarios_logros ul \r\n" + 
			//	"left join logros l on ul.logro = l.id \r\n" + 
				//"where ul.nickname like 'yarit'")
		//List<>
	}
}
