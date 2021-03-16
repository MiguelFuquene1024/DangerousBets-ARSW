package edu.escuelaing.arsw.dangerousbet.game;

public interface Juego {

    void jugar();
    void apostar(String nickanme,Integer valor) throws JuegoException;
    boolean verificar();
    String ganador();
    void abandonar(String nickanme);

}
