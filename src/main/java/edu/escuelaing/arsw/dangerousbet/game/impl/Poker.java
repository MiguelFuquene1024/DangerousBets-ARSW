package edu.escuelaing.arsw.dangerousbet.game.impl;

import edu.escuelaing.arsw.dangerousbet.game.Baraja;
import edu.escuelaing.arsw.dangerousbet.game.Juego;
import edu.escuelaing.arsw.dangerousbet.game.JuegoException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Poker implements Juego {


//    private List<Usuario> jugadores;
    private Baraja baraja;
    private Map<String, List<String>> cartas;
    private Map<String, Integer> apuestas;
    private Integer apuesta;
    private boolean estadoCartas;

    public Poker() {
        apuesta = 0;
        baraja = new Baraja();
        cartas = new HashMap<String, List<String>>();
        apuestas = new HashMap<String, Integer>();
        estadoCartas = false;
    }

    @Override
    public void jugar() {

//        while(true){
//            for (int i=0; i<jugadores.size();i++){
        repartir("jugador1 ");
        repartir("jugador2 ");
        repartir("jugador3 ");
//            }


//        }
    }

    @Override
    public void apostar(String nickanme, Integer valor) throws JuegoException {
        if (!apuestas.containsKey(nickanme)) {
            if (apuesta > valor) throw new JuegoException(JuegoException.DEBE_IGUALAR);
            apuestas.put(nickanme, valor);
            apuesta = valor;
        } else {
            Integer temp = apuestas.get(nickanme) + valor;
            if (apuesta > temp) throw new JuegoException(JuegoException.DEBE_IGUALAR);
            apuestas.put(nickanme, temp);
            apuesta = temp;
        }

    }

    @Override
    public boolean verificar() {
        return false;
    }

    @Override
    public String ganador() {
        return null;
    }

    @Override
    public void abandonar(String nickanme) {
        cartas.remove(nickanme);
    }

    public void repartir(String nickname) {
        cartas.put(nickname, baraja.getCarta());

    }

//    public void setJugadores(List<Usuario> jugadores) {
//        this.jugadores = jugadores;
//    }
//
//    public int getSizeJugadores() {
//        return jugadores.size();
//    }

    public Integer getApuestaByJugador(String nickname) {
        return apuestas.get(nickname);
    }

    public boolean isJugador(String nickname) {
        return cartas.containsKey(nickname);
    }
}
