package edu.escuelaing.arsw.dangerousbet.game;

import java.util.List;

public interface Juego {

    void jugar();
    void apostar(String nickanme,Integer valor) throws JuegoException;
    boolean verificar();
    String ganador();
    void abandonar(String nickanme);
    List<String> darCarta(int ronda);

}
