package edu.escuelaing.arsw.dangerousbet.game.impl;

import edu.escuelaing.arsw.dangerousbet.game.Baraja;
import edu.escuelaing.arsw.dangerousbet.game.Juego;
import edu.escuelaing.arsw.dangerousbet.game.JuegoException;
import edu.escuelaing.arsw.dangerousbet.game.Player;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Poker implements Juego {



    private List<Player> jugadores;
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
        jugadores = new ArrayList<>();
        estadoCartas = false;
    }

    public void iniciarPartida(List<String> nicknames, List<Integer> monedas){
        for(int i=0; i<nicknames.size();i++){
            Player player = new Player();
            player.setNickName(nicknames.get(i));
            player.setMoneda(monedas.get(i));
            jugadores.add(player);
        }

    }

    @Override
    public void jugar() {
        for(Player player: jugadores){
            if(player.isJugar()){
                repartir(player.getNickName());
                apuestas.put(player.getNickName(),0);
            }
        }

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

    @Override
    public List<String> darCarta(int ronda) {
        if (ronda>3){
            baraja.getCarta();
        }
        return baraja.getCarta();
    }

    public void repartir(String nickname) {
        cartas.put(nickname, baraja.getCarta());
    }

    public Integer getApuestaByJugador(String nickname) {
        return apuestas.get(nickname);
    }

    public boolean isJugador(String nickname) {
        return cartas.containsKey(nickname);
    }

    public List<Player> getJugadores() {
        return jugadores;
    }


}
