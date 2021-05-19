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
    private int apuestaTotalMesa;
    private VerificarGanadorPoker verificarGanadorPoker;
	private String estadoPartida;
	private String ganador;  
	private int apuestaObligatoria;
	private int turnoComenzante;

	
	
	private int ronda;
 
    public Poker() {
    	turnoComenzante=-1;
    	apuestaObligatoria=0;
        apuesta = 0;
        baraja = new Baraja();
        apuestas = new HashMap<String, Integer>();
        jugadores = new ArrayList<>();
        estadoCartas = false;
        verificarGanadorPoker= new VerificarGanadorPoker();
        estadoPartida="cargandoPagina";
    }

    public void iniciarPartida(List<String> nicknames, List<Integer> monedas){
        for(int i=0; i<nicknames.size();i++){
            Player player = new Player();
            player.setNickName(nicknames.get(i));
            player.setMoneda(monedas.get(i));
            player.setNumeroJugador(i+1);
            jugadores.add(player);
        }
        TimerTask timerTask = new TimerTask() { 
            @Override 
            public void run() { 
            	jugar();
            
            } 
           }; 
		Timer timerInicioJuego = new Timer(); 
		timerInicioJuego.schedule(timerTask,4000);
        

    }
    private void iniciarCronometro(){
    	 TimerTask timerTask = new TimerTask() { 

             @Override 
             public void run() { 
             	if (cronometro==0) {
             		jugadores.get(turno).setTurno(false);
             		if(jugadores.get(turno).getMisApuestas()<apuesta) {
             			abandonar();
             		}else {
             			cambiarTurno();
             		}
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
    	turnoComenzante+=1;
    	if(turnoComenzante==jugadores.size()) {
    		turnoComenzante=0;
    	}
    	while(jugadores.get(turnoComenzante).isEliminado()){
    		turnoComenzante+=1;
    		if(turnoComenzante==jugadores.size()) {
        		turnoComenzante=0;
        	}
    	}
    	if(turnoComenzante==0) {
    		apuestaObligatoria+=50;
    	}
    	
    	apuesta=0;
    	apuestaTotalMesa=0;
    	ronda=1;
    	cartasMesa=new ArrayList<>();
    	cartas = new HashMap<String, List<String>>();
        for(Player player: jugadores){
        		if(!player.isEliminado()) {
	        		player.setJugar(true);
	                repartir(player.getNickName());
        		}else {
        			player.setJugar(false);
        		}
                
            
        }
        resetApuestas();
        
        turno=turnoComenzante;
        try {
			apostarJuego(jugadores.get(turno).getNickName(),apuestaObligatoria);
		} catch (JuegoException e) {}
        jugadores.get(turno).setTurno(true);
        iniciarCronometro();
        estadoPartida="enPartida";
    }

    
    
    public Integer getApuesta() {
		return apuesta;
	}

	public void setApuesta(Integer apuesta) {
		this.apuesta = apuesta;
	}

	public int getCronometro() {
		return cronometro;
	}

	public void setCronometro(int cronometro) {
		this.cronometro = cronometro;
	}

	public int getApuestaTotalMesa() {
		return apuestaTotalMesa;
	}

	public void setApuestaTotalMesa(int apuestaTotalMesa) {
		this.apuestaTotalMesa = apuestaTotalMesa;
	}

	public String getEstadoPartida() {
		return estadoPartida;
	}

	public void setEstadoPartida(String estadoPartida) {
		this.estadoPartida = estadoPartida;
	}

	public List<List<String>> getCartasMesa() {
		return cartasMesa;
	}

	public void setJugadores(List<Player> jugadores) {
		this.jugadores = jugadores;
	}

	

	public void setCartasMesa(List<List<String>> cartasMesa) {
		this.cartasMesa = cartasMesa;
	}
	
	public void cambiarTurno() {
		jugadores.get(turno).setTurno(false);
    	timer.cancel();
    	turno+=1;
    	if(turno==jugadores.size()) {
    		turno=0;
    	}
    	if(turno==finRonda) {
    		darCarta();
    	}else {
    		pasarJugador();
    	}
	}
	public void pasarJugador() {
    	if(!jugadores.get(turno).isJugar()) {
    		cambiarTurno();
    	}
    	else {
	    	jugadores.get(turno).setTurno(true);
	    	iniciarCronometro();
    	}
    	
    	
    }
	private void sumarApuestas() {
		for(Player p:jugadores) {
			apuestaTotalMesa=apuestaTotalMesa+p.getMisApuestas();
            
		}
	}
	public void pasar() throws JuegoException {
		if (apuesta > apuestas.get(jugadores.get(turno).getNickName())) throw new JuegoException(JuegoException.DEBE_IGUALAR);
		cambiarTurno();
	}
    
    @Override
    public void apostar(String nickanme, Integer valor) throws JuegoException {
    	timer.cancel();
    	apostarJuego(nickanme,valor);
        cambiarTurno(); 

    }
    private void apostarJuego(String nickanme, Integer valor) throws JuegoException{
    	if(jugadores.get(turno).getMoneda() < valor) throw new JuegoException(JuegoException.NO_TIENE_SUFICIENTE_DINERO);
        Integer temp = apuestas.get(nickanme) + valor;
        
        if (apuesta > temp) throw new JuegoException(JuegoException.DEBE_IGUALAR);
        
        apuestas.put(nickanme, temp);

        jugadores.get(turno).setMisApuestas(temp);
        jugadores.get(turno).setMoneda(jugadores.get(turno).getMoneda()-valor);
        if(temp>apuesta) {
        	apuesta = temp;
            finRonda=turno;
        }
    	
    }
    	
    
    @Override
    public String verificar() {
        String ganador =verificarGanadorPoker.escaleraRealColor(cartasMesa,jugadores);
        if(ganador.equals("")){
            ganador=verificarGanadorPoker.poker(cartasMesa,jugadores);
            if(ganador.equals("")){
       
                ganador=verificarGanadorPoker.full(cartasMesa,jugadores);
                if(ganador.equals("")){
                    ganador=verificarGanadorPoker.colorCartas(cartasMesa,jugadores);
                    if(ganador.equals("")){
                        ganador=verificarGanadorPoker.trio(cartasMesa,jugadores);
                        if(ganador.equals("")){
                            ganador=verificarGanadorPoker.doblesParejas(cartasMesa,jugadores);
                            if(ganador.equals("")){
                                ganador=verificarGanadorPoker.pareajas(cartasMesa,jugadores);
                                if(ganador.equals("")){
                                    ganador=verificarGanadorPoker.cartaAlta(cartasMesa,jugadores);
                                }
                            }
                        }
                    }
                }
            }
        }
 
        return ganador;
    }

    @Override
    public String ganador() {
        return null;
    }

    @Override
    public void abandonar() {
    	timer.cancel();
    	getJugador(jugadores.get(turno).getNickName()).setJugar(false);
    	getJugador(jugadores.get(turno).getNickName()).setTurno(false);
    	cartas.remove(jugadores.get(turno).getNickName());
    	getJugador(jugadores.get(turno).getNickName()).setCartas(new ArrayList<>());
    	int cont=0;
    	Player playerWin=null;
    	for(Player p:jugadores) {
    		if(p.isJugar()) {
    			cont+=1;
    			playerWin=p;
    		}
    	}
    	if(cont==1) {
    		sumarApuestas();
    		darDineroGanador(playerWin);  		
    	}else {
    		cambiarTurno();
    	}
    }
    
    public void darDineroGanador(Player player) {
    	player.setMoneda(player.getMoneda()+apuestaTotalMesa);
    	for(Player p:jugadores) {
    		if(p.getMoneda()<=0) {
    			p.setEliminado(true);
    		}
    	}
    	if(jugadoresActivos()<=1) {
    		victoria();
    		
    	}else {
    		jugar();
    	}
    }
    
    @Override
    public void darCarta() {
    	
    	if(ronda==4){
    		estadoPartida="buscarGanador";
    		ganador = verificar();
    		sumarApuestas();
    		TimerTask timerTask = new TimerTask() { 
    			@Override 
                public void run() { 
    				for(Player p:jugadores) {
    					if(p.getNickName().equals(ganador)) {
    						darDineroGanador(p); 
    						break;
    					}
    				}
    				
    				
    			} 
            }; 
            Timer finRonda = new Timer(); 
            finRonda.schedule(timerTask,7000);
    		
    		
    	}
    	else {
			baraja.getCarta();
    		if (ronda==1){
	        	for(int i=0;i<3;i++){
	        		cartasMesa.add(baraja.getCarta());
	        	}	
    		}
    	
	    	else {
	        	cartasMesa.add(baraja.getCarta());
	        }
        	turno=turnoComenzante;
        	apuesta=0;
    		finRonda=turnoComenzante;
			ronda+=1;
			sumarApuestas();
			resetApuestas();
    		pasarJugador();
    	}
    }
    private void resetApuestas() {
    	for(Player p:jugadores) {
    		p.setMisApuestas(0);
    		apuestas.put(p.getNickName(),0);
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
	private int jugadoresActivos() {
		int cont=0;
		for(Player p:jugadores) {
			if(!p.isEliminado()) {
				cont+=1;
			}
		}
		return cont;
	}
	private void victoria() {
		
		estadoPartida="finJuego";
		TimerTask timerTask = new TimerTask() { 
			@Override 
            public void run() { 
				for(Player p:jugadores) {
					p.setEliminado(true);
					
				}
				
			} 
        }; 
        Timer finPartida = new Timer(); 
        finPartida.schedule(timerTask,4000);
	}
	public void eliminarJugador(String jugador) {
		Player p=getJugador(jugador);
		jugadores.get(p.getNumeroJugador()-1).setEliminado(true);
		cartas.remove(p.getNickName());
    	getJugador(p.getNickName()).setCartas(new ArrayList<>());
    	int cont=0;
    	p.setJugar(false);
    	p.setTurno(false);
    	Player playerWin=null;
 
	    	for(Player pl:jugadores) {
	    		if(pl.isJugar()) {
	    			cont+=1;
	    			playerWin=p;
	    		}
	    	}
	    	if(cont==1) {
	    		timer.cancel();
	    		sumarApuestas();
	    		darDineroGanador(playerWin);  		
	    	}else {
	    		if(p.getNumeroJugador()-1==turno) {
	    			cambiarTurno();
	    		}
	    	}
    	
		
		
	}

	
	

}
