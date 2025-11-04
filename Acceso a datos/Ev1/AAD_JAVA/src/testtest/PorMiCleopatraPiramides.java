package testtest;

import java.util.Scanner;


public class PorMiCleopatraPiramides {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int primerNumero = Integer.parseInt(s.nextLine());
        String[] entradas = new String[primerNumero];
        
        for (int i = 0; i < primerNumero; i++) {
        	entradas[i] = s.nextLine();
        }
        
        for (String entrada : entradas) {
        	String[] separado = entrada.split(" ");
        	
        	
        }
    }
}
