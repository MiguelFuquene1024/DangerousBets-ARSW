package edu.escuelaing.arsw.dangerousbet.game;

import java.util.List;

public interface Juego {

    void jugar();
    void apostar(String nickanme,Integer valor) throws JuegoException;
    String verificar();
    String ganador();
    void abandonar();
    void darCarta();

}
