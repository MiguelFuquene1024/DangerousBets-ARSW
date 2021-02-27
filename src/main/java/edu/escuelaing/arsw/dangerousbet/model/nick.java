package edu.escuelaing.arsw.dangerousbet.model;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;

import org.springframework.beans.factory.annotation.Autowired;

import edu.escuelaing.arsw.dangerousbet.dao.UserDao;

@Target({ElementType.FIELD})
@Documented
@Constraint(validatedBy={})
public @interface nick {
	
	
	boolean nickn();
}
