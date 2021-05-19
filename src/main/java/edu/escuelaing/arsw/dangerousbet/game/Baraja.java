package edu.escuelaing.arsw.dangerousbet.game;

import com.fasterxml.jackson.databind.annotation.JsonAppend;

import java.util.*;


public class Baraja {
    List<Integer> valores = new ArrayList<>();
    String[] casa = {"corazones","picas","treboles","diamantes"};
    HashMap<String,Boolean> cartasDisponibles;
    public Baraja() {

            for (int i = 1; i < 14; i++) {
                valores.add(i);
            }
    }

    public void reiniciarBaraja() {
    	cartasDisponibles=new HashMap<>();
    	for(String s:casa) {
    		for(int i = 1; i < 14; i++) {
    			String str=s+"-"+i;
    			cartasDisponibles.put(str,true);
    		}
    	}
    }

    public List<String> getCarta(){
    	int casaval;
    	String casaValor;
    	int valor;
	    do {
	    	Random r = new Random();
		    casaval =r.nextInt(4);
		    Random r2 = new Random();
		    valor = r2.nextInt(13);
		    casaValor=casa[casaval]+ "-" + String.valueOf(valores.get(valor)); 
	      }while(!cartasDisponibles.get(casaValor)) ;
	       List<String> list=new ArrayList<>();
	       list.add(casa[casaval]);
	       list.add(String.valueOf(valores.get(valor)));
	       cartasDisponibles.put(casaValor,false);

	       return list;
    }
}