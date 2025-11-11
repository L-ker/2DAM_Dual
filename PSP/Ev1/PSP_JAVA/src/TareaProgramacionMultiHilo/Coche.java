package TareaProgramacionMultiHilo;

import java.util.concurrent.LinkedBlockingQueue;

public class Coche extends Thread {
    private final String categoria;
    private final int peso;
    private final LinkedBlockingQueue<Coche> cola;

    public Coche(String categoria, LinkedBlockingQueue<Coche> cola) {
        this.categoria = categoria;
        this.peso = switch(categoria) {
        case "A" -> 100;
        case "B" -> 200;
        case "C" -> 300;
        default -> 0;
    };
        this.cola = cola;
    }

    @Override
    public void run() {
        System.out.println("Coche de cateogria" + categoria + " llega a la entrada del puente.");
        try {
            cola.put(this); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getCategoria() { return categoria; }
    public int getPeso() { return peso; }
}
