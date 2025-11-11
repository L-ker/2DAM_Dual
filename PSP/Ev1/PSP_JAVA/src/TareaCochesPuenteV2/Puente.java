package TareaCochesPuenteV2;
import java.util.concurrent.LinkedBlockingQueue;

public class Puente {
    private final LinkedBlockingQueue<Coche> cola;
    private final int MAX_COCHES = 10;
    private final int MAX_PESO = 200;
    private int cochesEnPuente = 0;
    private int pesoActual = 0;

    public Puente(LinkedBlockingQueue<Coche> cola) {
        this.cola = cola;
    }

    public void entrar(Coche coche) {
    	
    
    }
    
    public void salir(Coche coche) {
    	
    }
}