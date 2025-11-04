package testtest;

import java.util.ArrayList;
import java.util.Scanner;

public class PorMiPiramidePisos {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        
        ArrayList<Integer> listaNumeros = new ArrayList<Integer>();
        String linea = s.nextLine();
        while (!linea.equals("0")) {
        	listaNumeros.add(Integer.parseInt(linea));
        	linea = s.nextLine();
        }
        System.out.print(listaNumeros);
        
        for (int numero : listaNumeros) {
        	int pisos = 0;
        	int piedrasAnteriores = 0;
        	int piedras = 1;
        	int piedrasNivel = 1;
        	while (piedras < numero) {
        		piedras = (piedrasNivel * piedrasNivel);
        		piedrasAnteriores += piedras;
        		piedrasNivel += 2;
        		if (numero >= piedras) {
        			pisos++;
        		}
        	}
        	System.out.print(pisos);
        }
    }
}
