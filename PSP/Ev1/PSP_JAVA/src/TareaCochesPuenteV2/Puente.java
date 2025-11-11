package TareaCochesPuenteV2;
import java.util.concurrent.LinkedBlockingQueue;

public class Puente {
    private final LinkedBlockingQueue<Coche> cola;
    private final int MAX_COCHES = 10;
    private final int MAX_PESO = 200;
    private int cochesEnPuente = 0;
    private int pesoActual = 0;

    public Puente() {
    	cola = new LinkedBlockingQueue<Coche>();
    }

    /*
     * Sobre esto tengo una duda que quiero preguntarte en clase porque por lo que he visto esto funciona
     * pero se me hace "raro" que los coches deban gestionar otros coches en vez de a si mismos y quiero
     * saber si hay una mejor forma de hacerlo o una forma que parezca "tener mas sentido"
     */
    public void entrar(Coche coche) {
        try {
        	cola.put(coche);
        	Coche c = cola.take();
			
        	synchronized (this) {

                while (cochesEnPuente + 1 > MAX_COCHES || pesoActual + c.getPeso() > MAX_PESO) {
                    wait();
                }
                cochesEnPuente++;
                pesoActual += coche.getPeso();
                System.out.println("Coche " + coche.getCategoria() + " entrando al puente. Coches en puente: " + cochesEnPuente + ", peso: " + pesoActual);
        	}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    
    public void salir(Coche coche) {
        synchronized (this) {
            cochesEnPuente--;
            pesoActual -= coche.getPeso();
            System.out.println("Coche " + coche.getCategoria() + " ha salido del puente. Coches en puente: " + cochesEnPuente + ", peso: " + pesoActual);
            notifyAll();
        }
    }
}