package edu.escuelaing.arsw.dangerousbet.game.impl;

import edu.escuelaing.arsw.dangerousbet.game.Baraja;
import edu.escuelaing.arsw.dangerousbet.game.Juego;
import edu.escuelaing.arsw.dangerousbet.game.JuegoException;
import edu.escuelaing.arsw.dangerousbet.game.Player;
import edu.escuelaing.arsw.dangerousbet.security.entity.Salas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.Timer;

public class Poker implements Juego {


	private List<List<String>> cartasMesa;
    private List<Player> jugadores;
    private Baraja baraja;
    private Map<String, List<String>> cartas;
    private Map<String, Integer> apuestas;
    private Integer apuesta;
    private boolean estadoCartas;
    private int turno;
    private Timer timer;
    private int ronda;
    
    public Poker() {
        apuesta = 0;
        baraja = new Baraja();
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
    	ronda=1;
    	cartasMesa=new ArrayList<>();
    	cartas = new HashMap<String, List<String>>();
        for(Player player: jugadores){
            if(player.isJugar()){
                repartir(player.getNickName());
                apuestas.put(player.getNickName(),0);
            }
        }
        turno=0;
        jugadores.get(turno).setTurno(true);
        timer=new Timer(7000,new ActionListener() {


			@Override
			public void actionPerformed(ActionEvent e) {
						pasarJugador();
			}
		});
		timer.start();
    }

    
    
    public List<List<String>> getCartasMesa() {
		return cartasMesa;
	}

	public void setCartasMesa(List<List<String>> cartasMesa) {
		this.cartasMesa = cartasMesa;
	}

	public void pasarJugador() {
    	jugadores.get(turno).setTurno(false);
    	turno+=1;
    	if(turno==jugadores.size()) {
    		darCarta();
    		turno=0;
    		
    	}
    	jugadores.get(turno).setTurno(true);
    	timer.restart();
    	
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
    public void darCarta() {
    	baraja.getCarta();
    	if (ronda==1){
        	for(int i=0;i<3;i++) {
        		cartasMesa.add(baraja.getCarta());
        	}
        }
    	else if(ronda==3){
    		System.out.println("BUSCAR GANADOR");
    		timer.stop();
    	}
    	else {
        	cartasMesa.add(baraja.getCarta());
        }
    	ronda+=1;

    }

    public void repartir(String nickname) {
    	List<String> cartas = baraja.getCarta();
        this.cartas.put(nickname, cartas);
        getJugador(nickname).setCartas(cartas);
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

	public Player getJugador(String name) {
		for(Player p:jugadores) {
			if(name.equals(p.getNickName())) {
				return p;
			}
		}
		
		return null;
	}


}
