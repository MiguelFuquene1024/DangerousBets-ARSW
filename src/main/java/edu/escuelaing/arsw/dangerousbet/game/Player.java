package edu.escuelaing.arsw.dangerousbet.game;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String nickName;
    private int moneda;
    private int misApuestas;
    private boolean jugar;
    private List<String> cartas;
    private int numeroJugador;
    private boolean eliminado=false;
    private int victoriasConsecutivas;
    private int turnoSinJugar=0;
    private boolean turno;
    public Player() {
    	victoriasConsecutivas=0;
        jugar = true;
        turno=false;
        cartas=new ArrayList<>();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getMoneda() {
        return moneda;
    }

    public void setMoneda(int moneda) {
        this.moneda = moneda;
    }

    public boolean isJugar() {
        return jugar;
    }

    public void setJugar(boolean jugar) {
        this.jugar = jugar;
    }

	public List<String> obtenerCartas(){
		
		return cartas;
	}

	public void setCartas(List<String> cartas) {
		this.cartas = cartas;
	}

	public boolean isTurno() {
		return turno;
	}

	public void setTurno(boolean turno) {
		this.turno = turno;
	}

	public int getMisApuestas() {
		return misApuestas;
	}

	public void setMisApuestas(int misApuestas) {
		this.misApuestas = misApuestas;
	}

	public int getNumeroJugador() {
		return numeroJugador;
	}

	public void setNumeroJugador(int numeroJugador) {
		this.numeroJugador = numeroJugador;
	}

	public boolean isEliminado() {
		return eliminado;
	}

	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}

	public int getVictoriasConsecutivas() {
		return victoriasConsecutivas;
	}

	public void setVictoriasConsecutivas(int victoriasConsecutivas) {
		this.victoriasConsecutivas = victoriasConsecutivas;
	}

	public int getTurnoSinJugar() {
		return turnoSinJugar;
	}

	public void setTurnoSinJugar(int turnoSinJugar) {
		this.turnoSinJugar = turnoSinJugar;
	}
	
	
    
}
