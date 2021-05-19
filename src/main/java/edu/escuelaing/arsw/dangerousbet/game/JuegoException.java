package edu.escuelaing.arsw.dangerousbet.game;

public class JuegoException extends Exception{

    public static String NO_ES_TU_TURNO = "No es el turno de ese jugador";

	public JuegoException(String message){
        super(message);
    }
    public static String DEBE_IGUALAR="Se debe igualar al valor ya apostado o apostar mas";
    
    public static String NO_TIENE_SUFICIENTE_DINERO="No tiene suficiente dinero";
}
