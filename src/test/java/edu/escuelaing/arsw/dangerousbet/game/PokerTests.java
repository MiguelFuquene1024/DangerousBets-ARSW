package edu.escuelaing.arsw.dangerousbet.game;

import edu.escuelaing.arsw.dangerousbet.game.impl.Poker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PokerTests {

    @Test
    public void agregarJugadores(){
        Poker poker = new Poker();
        List<String> nickNames = new ArrayList<>();
        List<Integer> monedas = new ArrayList<>();
        nickNames.add("Juan");
        nickNames.add("Andres");
        monedas.add(1000);
        monedas.add(2000);
        poker.iniciarPartida(nickNames,monedas);

        Assertions.assertEquals(poker.getJugadores().size(), nickNames.size());
    }

    @Test
    public void apostarConUsuarios() throws JuegoException {
        Poker poker = new Poker();
        List<String> nickNames = new ArrayList<>();
        List<Integer> monedas = new ArrayList<>();
        nickNames.add("Juan");
        nickNames.add("Andres");
        monedas.add(1000);
        monedas.add(2000);
        poker.iniciarPartida(nickNames,monedas);
        Random r = new Random();
        int apuesta1 =r.nextInt(200);
        int apuesta2 =r.nextInt(200);
        poker.apostar("Juan",apuesta1);
        poker.apostar("Juan",apuesta2);
        Assertions.assertTrue(poker.getApuestaByJugador("Juan")==(apuesta1+apuesta2));
    }
/*
    @Test
    public void abandonarRondaSinrecibirCartas(){
        Poker poker = new Poker();
        List<String> nickNames = new ArrayList<>();
        List<Integer> monedas = new ArrayList<>();
        nickNames.add("Juan");
        nickNames.add("Andres");
        nickNames.add("Mario");
        monedas.add(1000);
        monedas.add(2000);
        monedas.add(2000);
        poker.iniciarPartida(nickNames,monedas);
        poker.jugar();
        poker.abandonar();
        Assertions.assertTrue((poker.isJugador("Juan") && poker.isJugador("Andres") && !poker.isJugador("Mario")) );
    }
    

    @Test
    public void abandonarRondaConCartasRecibidas(){
        Poker poker = new Poker();
        List<String> nickNames = new ArrayList<>();
        List<Integer> monedas = new ArrayList<>();
        nickNames.add("Juan");
        nickNames.add("Andres");
        nickNames.add("Mario");
        monedas.add(1000);
        monedas.add(2000);
        monedas.add(2000);
        poker.iniciarPartida(nickNames,monedas);
        Random r = new Random();
        int ite =r.nextInt(nickNames.size());
        poker.jugar();
        poker.abandonar(nickNames.get(ite));
        Assertions.assertTrue(!poker.isJugador(nickNames.get(ite)));

    }
*/
    @Test
    public void seDebeIgualarLaApuesta(){
        Poker poker = new Poker();
        List<String> nickNames = new ArrayList<>();
        List<Integer> monedas = new ArrayList<>();
        nickNames.add("Juan");
        nickNames.add("Andres");
        nickNames.add("Mario");
        monedas.add(1000);
        monedas.add(2000);
        monedas.add(2000);
        poker.iniciarPartida(nickNames,monedas);
        try {
            poker.apostar("Juan",200);
            poker.apostar("Andres",150);
        } catch (JuegoException e) {
            Assertions.assertTrue(e.getMessage().equals(JuegoException.DEBE_IGUALAR));
        }

    }

    @Test
    public void seDebeIgualarOApostarMas(){
        Poker poker = new Poker();
        List<String> nickNames = new ArrayList<>();
        List<Integer> monedas = new ArrayList<>();
        nickNames.add("Juan");
        nickNames.add("Andres");
        nickNames.add("Mario");
        monedas.add(1000);
        monedas.add(2000);
        monedas.add(2000);
        poker.iniciarPartida(nickNames,monedas);
        try {
            poker.apostar("Juan",200);
            poker.apostar("Andres",200);
            poker.apostar("Juan",25);
            Assertions.assertTrue(poker.getApuestaByJugador("Juan")==225);
        } catch (JuegoException e) {
            Assertions.fail();
        }
    }



}
