package edu.escuelaing.arsw.dangerousbet.game;

import com.fasterxml.jackson.databind.annotation.JsonAppend;

import java.util.*;


public class Baraja {
    List<Integer> valores = new ArrayList<>();
    String[] casa = {"corazones","picas","treboles","diamantes"};

    public Baraja() {

            for (int i = 1; i < 14; i++) {
                valores.add(i);
            }
    }



    public List<String> getCarta(){
        List<String> list=new ArrayList<>();
        Random r = new Random();
        int casaval =r.nextInt(4);
        Random r2 = new Random();
        int valor = r2.nextInt(13);
        list.add(casa[casaval]);
        list.add(String.valueOf(valores.get(valor)));
        

        return list;
    }
}