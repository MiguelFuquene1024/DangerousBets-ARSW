package edu.escuelaing.arsw.dangerousbet.model;

import java.lang.annotation.Annotation;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import edu.escuelaing.arsw.dangerousbet.dao.UserDao;

public class anotacion implements nick{

	
	@Autowired
	private UserDao uc;

	@Override
	public Class<? extends Annotation> annotationType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean nickn() {
	
		return false;
		
	}
	


}
