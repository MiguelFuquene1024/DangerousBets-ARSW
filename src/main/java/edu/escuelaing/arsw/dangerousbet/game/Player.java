package edu.escuelaing.arsw.dangerousbet.game;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String nickName;
    private int moneda;
    private int misApuestas;
    private boolean jugar;
    private List<String> cartas;
    private boolean turno;
    public Player() {
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

	public List<String> getCartas() {
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
    
}
