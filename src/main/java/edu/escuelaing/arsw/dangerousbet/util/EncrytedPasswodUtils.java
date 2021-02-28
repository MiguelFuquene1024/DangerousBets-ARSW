package edu.escuelaing.arsw.dangerousbet.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncrytedPasswodUtils {

    public static String encryted(String password){
        BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
        return encoder.encode(password);
    }


}
