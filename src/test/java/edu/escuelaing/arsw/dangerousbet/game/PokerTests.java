package edu.escuelaing.arsw.dangerousbet.game;

import edu.escuelaing.arsw.dangerousbet.game.impl.Poker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PokerTests {

//    @Test
//    public void agregarJugadores(){
//        Poker poker = new Poker();
//        List<Usuario> jugadores= new ArrayList<>();
//        Usuario jugador1= new Usuario("jugador1");
//        Usuario jugador2= new Usuario("jugador2");
//        jugadores.add(jugador1);
//        jugadores.add(jugador2);
//        poker.setJugadores(jugadores);
//        Assertions.assertTrue(poker.getSizeJugadores()==jugadores.size());
//    }
//
//    @Test
//    public void apostarConUsuarios() throws JuegoException {
//        Poker poker = new Poker();
//        List<Usuario> jugadores= new ArrayList<>();
//        Usuario jugador1= new Usuario("jugador1");
//        Usuario jugador2= new Usuario("jugador2");
//        jugadores.add(jugador1);
//        jugadores.add(jugador2);
//        poker.setJugadores(jugadores);
//        Random r = new Random();
//        int apuesta1 =r.nextInt(200);
//        int apuesta2 =r.nextInt(200);
//        poker.apostar("jugador1",apuesta1);
//        poker.apostar("jugador1",apuesta2);
//        Assertions.assertTrue(poker.getApuestaByJugador("jugador1")==(apuesta1+apuesta2));
//    }
//
//    @Test
//    public void abandonarRondaSinrecibirCartas(){
//        Poker poker = new Poker();
//        List<Usuario> jugadores= new ArrayList<>();
//        Usuario jugador1= new Usuario("jugador1");
//        Usuario jugador2= new Usuario("jugador2");
//        Usuario jugador3= new Usuario("jugador3");
//        jugadores.add(jugador1);
//        jugadores.add(jugador2);
//        jugadores.add(jugador3);
//        poker.setJugadores(jugadores);
//        poker.repartir("jugador1");
//        poker.repartir("jugador2");
//        Assertions.assertTrue((poker.isJugador("jugador1") && poker.isJugador("jugador2") && !poker.isJugador("jugador3")) );
//    }
//
//    @Test
//    public void abandonarRondaConCartasRecibidas(){
//        Poker poker = new Poker();
//        List<Usuario> jugadores= new ArrayList<>();
//        Usuario jugador1= new Usuario("jugador1");
//        Usuario jugador2= new Usuario("jugador2");
//        Usuario jugador3= new Usuario("jugador3");
//        jugadores.add(jugador1);
//        jugadores.add(jugador2);
//        jugadores.add(jugador3);
//        poker.setJugadores(jugadores);
//        Random r = new Random();
//        int ite =r.nextInt(jugadores.size());
//        poker.repartir("jugador1");
//        poker.repartir("jugador2");
//        poker.repartir("jugador3");
//
//        poker.abandonar(jugadores.get(ite).getNickname());
//        Assertions.assertTrue(!poker.isJugador(jugadores.get(ite).getNickname()));
//
//    }
//
//    @Test
//    public void seDebeIgualarLaApuesta(){
//        Poker poker = new Poker();
//        List<Usuario> jugadores= new ArrayList<>();
//        Usuario jugador1= new Usuario("jugador1");
//        Usuario jugador2= new Usuario("jugador2");
//        Usuario jugador3= new Usuario("jugador3");
//        jugadores.add(jugador1);
//        jugadores.add(jugador2);
//        jugadores.add(jugador3);
//        try {
//            poker.apostar("jugador1",200);
//            poker.apostar("jugador2",150);
//        } catch (JuegoException e) {
//            Assertions.assertTrue(e.getMessage().equals(JuegoException.DEBE_IGUALAR));
//        }
//
//    }
//
//    @Test
//    public void seDebeIgualarOApostarMas(){
//        Poker poker = new Poker();
//        List<Usuario> jugadores= new ArrayList<>();
//        Usuario jugador1= new Usuario("jugador1");
//        Usuario jugador2= new Usuario("jugador2");
//        Usuario jugador3= new Usuario("jugador3");
//        jugadores.add(jugador1);
//        jugadores.add(jugador2);
//        jugadores.add(jugador3);
//        try {
//            poker.apostar("jugador1",200);
//            poker.apostar("jugador2",200);
//            poker.apostar("jugador1",25);
//            Assertions.assertTrue(poker.getApuestaByJugador("jugador1")==225);
//        } catch (JuegoException e) {
//            Assertions.fail();
//        }
//    }



}
