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

	}
}
