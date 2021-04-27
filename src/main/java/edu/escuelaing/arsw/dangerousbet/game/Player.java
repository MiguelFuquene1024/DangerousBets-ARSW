package edu.escuelaing.arsw.dangerousbet.game;

public class Player {
    private String nickName;
    private int moneda;
    private boolean jugar;

    public Player() {
        jugar = true;
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
}
