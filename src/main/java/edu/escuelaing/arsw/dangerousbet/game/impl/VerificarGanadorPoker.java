package edu.escuelaing.arsw.dangerousbet.game.impl;

import edu.escuelaing.arsw.dangerousbet.game.Player;
import edu.escuelaing.arsw.dangerousbet.game.VerificarGanador;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VerificarGanadorPoker implements VerificarGanador {

    @Override
    public String escaleraRealColor(List<List<String>> cartasMesa, List<Player> jugadores) {
        String ganador = "";
        for(Player p : jugadores){
            if(p.isJugar() && verificarColor(cartasMesa,p.obtenerCartas())){
                if(escaleraRealverificar(cartasMesa,p.obtenerCartas())){
                    ganador = p.getNickName();
                }
            }
        }
        return ganador;
    }

    @Override
    public String escaleraDeColor(List<List<String>> cartasMesa, List<Player> jugadores) {
    	String ganador = "";
    	for (Player pj: jugadores){
            if(pj.isJugar() && verificarColor(cartasMesa,pj.obtenerCartas())) {
                List<Integer> list = valoreColorCartas(cartasMesa,pj.obtenerCartas());
                List<Integer> list2 = list.stream().sorted().collect(Collectors.toList());
                if(compararEscalera(list2)){
                    ganador=pj.getNickName();
                }
            }
        }
        return ganador;
    }

    private boolean compararEscalera(List<Integer> list2) {
        int inicio = list2.get(0);
        boolean verificar = true;
        for(int i=1 ; i<list2.size()-1 && verificar; i++){
            if(!(inicio+i==list2.get(i))){
                verificar=false;
            }
        }
        return verificar;
    }

    @Override
    public String poker(List<List<String>> cartasMesa, List<Player> jugadores) {
        String ganador = "";
        for(Player p : jugadores){
            if(p.isJugar() && verificarPoker(cartasMesa,p.obtenerCartas())){
                ganador=p.getNickName();
            }
        }
        return ganador;
    }

    @Override
    public String full(List<List<String>> cartasMesa, List<Player> jugadores) {
        String ganador = "";
        for(Player p : jugadores){
            if(p.isJugar() && verificarTrios(cartasMesa,p.obtenerCartas())){
                if(verificarPares(cartasMesa,p.obtenerCartas())){
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
            if(p.isJugar() && verificarColor(cartasMesa,p.obtenerCartas())){
                ganador=p.getNickName();
            }
        }
        return ganador;
    }

    @Override
    public String escalera(List<List<String>> cartasMesa, List<Player> jugadores) {
    	String ganador = "";
    	for(Player pj: jugadores) {
            List<Integer> list = valoresCartas(cartasMesa, pj.obtenerCartas());
            List<Integer> list2 = list.stream().sorted().collect(Collectors.toList());
            if(compararEscalera(list2)){
                ganador=pj.getNickName();
            }
        }
        return ganador;
    }

    private List<Integer> valoresCartas(List<List<String>> cartasMesa, List<String> cartas) {
        List<Integer> list = new ArrayList<>();
        list.add(Integer.valueOf(cartas.get(1)));
        list.add(Integer.valueOf(cartas.get(3)));
        for(List<String> li:cartasMesa){
            list.add(Integer.valueOf(li.get(1)));
        }
        return list;
    }

    @Override
    public String trio(List<List<String>> cartasMesa, List<Player> jugadores) {
        String ganador = "";
        for(Player p : jugadores){
            if(p.isJugar() && verificarTrios(cartasMesa,p.obtenerCartas())){
                ganador=p.getNickName();
            }
        }
        return ganador;
    }

    @Override
    public String doblesParejas(List<List<String>> cartasMesa, List<Player> jugadores) {
    	String ganador = "";
        for(Player p : jugadores){
            if(p.isJugar() && verificarDoblePares(cartasMesa,p.obtenerCartas())){
                ganador=p.getNickName();
            }
        }
        return ganador;
    }

    @Override
    public String pareajas(List<List<String>> cartasMesa, List<Player> jugadores) {
        String ganador = "";
        for(Player p : jugadores){
            if(p.isJugar() && verificarPares(cartasMesa,p.obtenerCartas())){
                ganador=p.getNickName();
            }
        }
        return ganador;
    }

    @Override
    public String cartaAlta(List<List<String>> cartasMesa, List<Player> jugadores) {
        String ganador = jugadores.get(0).getNickName();
        int maxcarta= Math.max(Integer.parseInt(jugadores.get(0).obtenerCartas().get(1)),
                Integer.parseInt(jugadores.get(0).obtenerCartas().get(3)));
        for(int i =1; i<jugadores.size();i++){
        	if(jugadores.get(i).isJugar()) {
	            int tempmax=Math.max(Integer.parseInt(jugadores.get(i).obtenerCartas().get(1)),
	                    Integer.parseInt(jugadores.get(i).obtenerCartas().get(3)));
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

    private List<Integer> valoreColorCartas(List<List<String>> cartasMesa, List<String> cartasJuagador){
        List<Integer> res= new ArrayList<>();
        String color1=cartasJuagador.get(0);
        String color2=cartasJuagador.get(2);
        if(color1.equals(color2)){
            res.add(Integer.valueOf(cartasJuagador.get(1)));
            res.add(Integer.valueOf(cartasJuagador.get(3)));
            for (List<String> carta : cartasMesa){
                if (carta.get(0).equals(color1)){
                    res.add(Integer.valueOf(carta.get(1)));
                }
            }
        }
        else{
            List<Integer> temp1=new ArrayList<>();
            List<Integer> temp2=new ArrayList<>();
            temp1.add(Integer.valueOf(cartasJuagador.get(1)));
            temp2.add(Integer.valueOf(cartasJuagador.get(3)));
            for (List<String> carta : cartasMesa){
                if (carta.get(0).equals(color1)){
                    temp1.add(Integer.valueOf(carta.get(1)));
                }
            }
            for (List<String> carta : cartasMesa){
                if (carta.get(0).equals(color2)){
                    temp2.add(Integer.valueOf(carta.get(1)));
                }
            }
           if(temp1.size()>temp2.size()){
               res = temp1;
           }
           else{
               res =temp2;
           }
        }

        return res;

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
