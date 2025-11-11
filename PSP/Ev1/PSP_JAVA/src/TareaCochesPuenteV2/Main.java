package TareaCochesPuenteV2;

import java.util.concurrent.LinkedBlockingQueue;

public class Main {

	public static void main(String[] args) {
		LinkedBlockingQueue<Coche> cola = new LinkedBlockingQueue<>();

        Puente puente = new Puente();

        new Coche("B", puente).start();
        new Coche("A", puente).start();
        new Coche("C", puente).start();
        new Coche("A", puente).start();
        new Coche("B", puente).start();
        new Coche("A", puente).start();
        new Coche("C", puente).start();
        new Coche("B", puente).start();
        new Coche("A", puente).start();
        new Coche("A", puente).start();
        new Coche("C", puente).start();
        new Coche("B", puente).start();
        new Coche("A", puente).start();

	}

}
