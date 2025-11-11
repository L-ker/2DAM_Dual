package TareaProgramacionMultiHilo;

import java.util.concurrent.LinkedBlockingQueue;

public class Main {

	public static void main(String[] args) {
		LinkedBlockingQueue<Coche> cola = new LinkedBlockingQueue<>();

        new Puente(cola).start();

        new Coche("A", cola).start();
        new Coche("B", cola).start();
        new Coche("A", cola).start();
        new Coche("C", cola).start();
        new Coche("A", cola).start();
        new Coche("B", cola).start();
        new Coche("A", cola).start();
        new Coche("C", cola).start();
        new Coche("B", cola).start();
        new Coche("A", cola).start();

	}

}
