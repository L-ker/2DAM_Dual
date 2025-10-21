package HilosTest;

import java.util.ArrayList;

public class Main2 {
	
	public static void main(String[] args) {
		Contador c = new Contador(0);
		
		final int MAXHILOS = 10;
		
		ArrayList<Thread> listaHilos = new ArrayList<>();
		
		for (int i = 0; i < MAXHILOS; i++) {
			Thread t = new Thread(new Hilo2(c));
			listaHilos.add(t);
			t.start();
		}
		
		for(Thread t:listaHilos) {
			try {
				t.join();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println(c);
	}	
}
