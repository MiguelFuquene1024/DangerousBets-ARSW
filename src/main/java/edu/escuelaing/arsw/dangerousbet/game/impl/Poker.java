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


import java.util.Timer; 
import java.util.TimerTask; 


public class Poker implements Juego {


	private List<List<String>> cartasMesa;
    private List<Player> jugadores;
    private Baraja baraja;
    private Map<String, List<String>> cartas;
    private Map<String, Integer> apuestas;
    private Integer apuesta;
    private boolean estadoCartas;
    private int turno;
    private int finRonda;  
    private Timer timer; 
    private int cronometro;
    
    	
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
    private void iniciarCronometro(){
    	 TimerTask timerTask = new TimerTask() { 

             @Override 
             public void run() { 
             	if (cronometro==0) {
             		pasarJugador();
             	}else {
             		cronometro-=1;
             	}
             	
             } 
            }; 
        cronometro=25;
 		timer = new Timer(); 
 	    timer.schedule(timerTask,1000,1000); 
    }
    
    
    @Override
    public void jugar() {
    	ronda=1;
    	
    	cartasMesa=new ArrayList<>();
    	cartas = new HashMap<String, List<String>>();
    	
        for(Player player: jugadores){
        		/*try {
        			System.out.println("//////////////////////////////////////////////////");
					this.wait(2000);
				} catch (InterruptedException e1) {
					System.out.println("no espere");
				}*/
        		player.setJugar(true);
                repartir(player.getNickName());
                apuestas.put(player.getNickName(),0);
            
        }
        turno=0;
        finRonda=0;
        jugadores.get(turno).setTurno(true);
        iniciarCronometro();
    }

    
    
    public  List<Object> EstadoActualJuego() {
    	List<Object> mesaActual=new ArrayList<>();
    	mesaActual.add(cronometro);
    	mesaActual.add(jugadores);
    	mesaActual.add(cartasMesa);
    	mesaActual.add(apuesta);
		return mesaActual;
	}

	public void setCartasMesa(List<List<String>> cartasMesa) {
		this.cartasMesa = cartasMesa;
	}

	public void pasarJugador() {
    	jugadores.get(turno).setTurno(false);
    	timer.cancel();
    	turno+=1;
    	if(turno==jugadores.size()) {
    		turno=0;
    	}
    	if(turno==finRonda) {
    		darCarta();
    		
    	}
    	else if(!jugadores.get(turno).isJugar()) {
    		pasarJugador();
    	}
    	else {
	    	jugadores.get(turno).setTurno(true);
	    	iniciarCronometro();
    	}
    	
    	
    }
	
	public void pasar() throws JuegoException {
		if (apuesta > apuestas.get(jugadores.get(turno).getNickName())) throw new JuegoException(JuegoException.DEBE_IGUALAR);
		pasarJugador();
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
            if(temp>apuesta) {
            	apuesta = temp;
                finRonda=turno;
            }
            pasarJugador();
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
    public void abandonar() {
    	getJugador(jugadores.get(turno).getNickName()).setJugar(false);
    	cartas.remove(jugadores.get(turno).getNickName());
    	getJugador(jugadores.get(turno).getNickName()).setCartas(new ArrayList<>());
    }

    @Override
    public void darCarta() {
    	baraja.getCarta();
    	if(ronda==4){
    		System.out.println("BUSCAR GANADOR");
    		jugar();
    	}
    	else {
    		if (ronda==1){
	        	for(int i=0;i<3;i++){
	        		cartasMesa.add(baraja.getCarta());
	        	}	
    		}
    	
	    	else {
	        	cartasMesa.add(baraja.getCarta());
	        }
        	turno=0;
    		finRonda=0;
    		ronda+=1;
    		jugadores.get(turno).setTurno(true);
    		iniciarCronometro();
    		
    	}
    	

    }

    public void repartir(String nickname) {
    	List<String> cartas = baraja.getCarta();
    	cartas.addAll(baraja.getCarta());
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

	public void apostar2(int apuesta2) throws JuegoException {
		apostar(jugadores.get(turno).getNickName(),apuesta2);
		
	}

	
	

}
