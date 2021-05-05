package edu.escuelaing.arsw.dangerousbet.game;

import java.util.List;

public interface VerificarGanador {

    public String escaleraRealColor(List<List<String>> cartasMesa,List<Player> jugadores);
    public String escaleraDeColor(List<List<String>> cartasMesa, List<Player> jugadores);
    public String poker(List<List<String>> cartasMesa,List<Player> jugadores);
    public String full(List<List<String>> cartasMesa,List<Player> jugadores);
    public String colorCartas(List<List<String>> cartasMesa,List<Player> jugadores);
    public String escalera(List<List<String>> cartasMesa,List<Player> jugadores);
    public String trio(List<List<String>> cartasMesa,List<Player> jugadores);
    public String doblesParejas(List<List<String>> cartasMesa,List<Player> jugadores);
    public String pareajas(List<List<String>> cartasMesa,List<Player> jugadores);
    public String cartaAlta(List<List<String>> cartasMesa,List<Player> jugadores);

}
