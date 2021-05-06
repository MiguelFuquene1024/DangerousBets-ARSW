package edu.escuelaing.arsw.dangerousbet.game.impl;

import edu.escuelaing.arsw.dangerousbet.game.Player;
import edu.escuelaing.arsw.dangerousbet.game.VerificarGanador;

import java.util.ArrayList;
import java.util.List;

public class VerificarGanadorPoker implements VerificarGanador {

    @Override
    public String escaleraRealColor(List<List<String>> cartasMesa, List<Player> jugadores) {
        String ganador = "";
        for(Player p : jugadores){
            if(p.isJugar() && verificarColor(cartasMesa,p.getCartas())){
                if(escaleraRealverificar(cartasMesa,p.getCartas())){
                    ganador = p.getNickName();
                }
            }
        }
        return ganador;
    }

    @Override
    public String escaleraDeColor(List<List<String>> cartasMesa, List<Player> jugadores) {
    	String ganador = "";
        return ganador;
    }

    @Override
    public String poker(List<List<String>> cartasMesa, List<Player> jugadores) {
        String ganador = "";
        for(Player p : jugadores){
            if(p.isJugar() && verificarPoker(cartasMesa,p.getCartas())){
                ganador=p.getNickName();
            }
        }
        return ganador;
    }

    @Override
    public String full(List<List<String>> cartasMesa, List<Player> jugadores) {
        String ganador = "";
        for(Player p : jugadores){
            if(p.isJugar() && verificarTrios(cartasMesa,p.getCartas())){
                if(verificarPares(cartasMesa,p.getCartas())){
                    ganador=p.getNickName();
                }

            }
        }
        return ganador;
    }

    @Override
    public String colorCartas(List<List<String>> cartasMesa, List<Player> jugadores) {
        String ganador = "";
        for(Player p : jugadores){
            if(p.isJugar() && verificarColor(cartasMesa,p.getCartas())){
                ganador=p.getNickName();
            }
        }
        return ganador;
    }

    @Override
    public String escalera(List<List<String>> cartasMesa, List<Player> jugadores) {
    	String ganador = "";
        return ganador;
    }

    @Override
    public String trio(List<List<String>> cartasMesa, List<Player> jugadores) {
        String ganador = "";
        for(Player p : jugadores){
            if(p.isJugar() && verificarTrios(cartasMesa,p.getCartas())){
                ganador=p.getNickName();
            }
        }
        return ganador;
    }

    @Override
    public String doblesParejas(List<List<String>> cartasMesa, List<Player> jugadores) {
    	String ganador = "";
        return ganador;
    }

    @Override
    public String pareajas(List<List<String>> cartasMesa, List<Player> jugadores) {
        String ganador = "";
        for(Player p : jugadores){
            if(p.isJugar() && verificarPares(cartasMesa,p.getCartas())){
                ganador=p.getNickName();
            }
        }
        return ganador;
    }

    @Override
    public String cartaAlta(List<List<String>> cartasMesa, List<Player> jugadores) {
        String ganador = jugadores.get(0).getNickName();
        int maxcarta= Math.max(Integer.parseInt(jugadores.get(0).getCartas().get(1)),
                Integer.parseInt(jugadores.get(0).getCartas().get(3)));
        for(int i =1; i<jugadores.size();i++){
        	if(jugadores.get(i).isJugar()) {
	            int tempmax=Math.max(Integer.parseInt(jugadores.get(i).getCartas().get(1)),
	                    Integer.parseInt(jugadores.get(i).getCartas().get(3)));
	            if(maxcarta<tempmax){
	                ganador=jugadores.get(i).getNickName();
	                maxcarta=tempmax;
	            }
        	}

        }
        return ganador;
    }

    private boolean verificarColor(List<List<String>> cartasMesa, List<String> cartasJuagador){
        int count=0;
        String color1=cartasJuagador.get(0);
        String color2=cartasJuagador.get(2);
        if(color1.equals(color2)){
            count=2;
            for (List<String> carta : cartasMesa){
                if (carta.get(0).equals(color1)){
                    count++;
                }
            }
        }
        else{
            int temp1=1;
            int temp2=1;
            for (List<String> carta : cartasMesa){
                if (carta.get(0).equals(color1)){
                    temp1++;
                }
            }
            for (List<String> carta : cartasMesa){
                if (carta.get(0).equals(color2)){
                    temp2++;
                }
            }
            count = Math.max(temp1, temp2);
        }

        return count>=5;
    }

    private boolean escaleraRealverificar(List<List<String>> cartasMesa, List<String> cartasJuagador){
        boolean value= false;
        List<String> cartasVerificar= new ArrayList<>();
        cartasVerificar.add(cartasJuagador.get(1));
        cartasVerificar.add(cartasJuagador.get(3));
        for (List<String> ls: cartasMesa ){
            cartasVerificar.add(ls.get(0));
        }
        value=(cartasVerificar.contains("1")
                && cartasVerificar.contains("10")
                && cartasVerificar.contains("11")
                && cartasVerificar.contains("12") &&
                cartasVerificar.contains("13"));
        return value;
    }

    private boolean verificarPoker(List<List<String>> cartasMesa, List<String> cartasJuagador){
        int count = 0;
        String carta1=cartasJuagador.get(1);
        String carta2=cartasJuagador.get(3);
        if (carta1.equals(carta2)){
            count=2;
            for (List<String> carta : cartasMesa){
                if(carta.get(1).equals(carta1)){
                    count++;
                }
            }
        }
        else{
            int temp1=1;
            int temp2=1;
            for (List<String> carta : cartasMesa){
                if(carta.get(1).equals(carta1)){
                    temp1++;
                }
                else if(carta.get(1).equals(carta2)){
                    temp2++;
                }
            }
            count = Math.max(temp1, temp2);
        }
        return count==4;
    }
    public boolean verificarPares(List<List<String>> cartasMesa, List<String> cartasJuagador){
        int count = 0;
        String carta1=cartasJuagador.get(1);
        String carta2=cartasJuagador.get(3);
        if (carta1.equals(carta2)){
            count =2;
        }
        else{
            int temp1=1;
            int temp2=1;
            for (List<String> carta : cartasMesa){
                if(carta.get(1).equals(carta1)){
                    temp1++;
                }
                else if(carta.get(1).equals(carta2)){
                    temp2++;
                }
            }
            if(temp1==2 || temp2==2){
                count=2;
            }

        }
        return count==2;
    }

    public boolean verificarDoblePares(List<List<String>> cartasMesa, List<String> cartasJuagador){
        int count = 0;
        String carta1=cartasJuagador.get(1);
        String carta2=cartasJuagador.get(3);
        if (carta1.equals(carta2)){
            count =2;
            int temp1=0;
            for (int i=0;i<cartasMesa.size();i++){
                temp1=1;
                for(int j=i+1;j<cartasMesa.size();j++){
                    if (cartasMesa.get(i).get(1).equals(cartasMesa.get(j).get(1))){
                        temp1++;
                    }
                }
                if(temp1==2){
                    count=4;
                }
            }
        }
        else{
            int temp1=1;
            int temp2=1;
            for (List<String> carta : cartasMesa){
                if(carta.get(1).equals(carta1)){
                    temp1++;
                }
                else if(carta.get(1).equals(carta2)){
                    temp2++;
                }
            }
            if(temp1==2 && temp2==2){
                count=4;
            }
        }
        return count == 4;
    }

    public boolean verificarTrios(List<List<String>> cartasMesa, List<String> cartasJuagador){
        int count = 0;
        String carta1=cartasJuagador.get(1);
        String carta2=cartasJuagador.get(3);
        if (carta1.equals(carta2)){
            count =2;
            for (List<String> carta : cartasMesa){
                if(carta.get(1).equals(carta1)){
                    count++;
                }
            }
        }
        else{
            int temp1=1;
            int temp2=1;
            for (List<String> carta : cartasMesa){
                if (carta.get(1).equals(carta1)){
                    temp1++;
                }
                else if (carta.get(1).equals(carta2)){
                    temp2++;
                }
            }
            count=Math.max(temp1,temp2);

        }

        return count==3;

    }


}
