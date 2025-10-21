package HilosTest;

import java.util.ArrayList;

public class Main {
	
	public static void main(String[] args) {
		Contador c = new Contador(0);
		
		final int MAXHILOS = 10;
		
		ArrayList<Hilo1> listaHilos = new ArrayList<>();
		
		for (int i = 0; i < MAXHILOS; i++) {
			Hilo1 h = new Hilo1(c);
			listaHilos.add(h);
			h.start();
		}
		
		for( Hilo1 h:listaHilos) {
			try {
				h.join();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println(c);
	}	
}
