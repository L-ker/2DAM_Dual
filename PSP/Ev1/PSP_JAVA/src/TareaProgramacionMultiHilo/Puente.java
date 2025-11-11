package TareaProgramacionMultiHilo;
import java.util.concurrent.LinkedBlockingQueue;

public class Puente extends Thread {
    private final LinkedBlockingQueue<Coche> cola;
    private final int MAX_COCHES = 10;
    private final int MAX_PESO = 200;
    private int cochesEnPuente = 0;
    private int pesoActual = 0;

    public Puente(LinkedBlockingQueue<Coche> cola) {
        this.cola = cola;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Coche c = cola.take();

                synchronized (this) {
                    while (cochesEnPuente + 1 > MAX_COCHES || pesoActual + c.getPeso() > MAX_PESO) {
                        wait();
                    }
                    cochesEnPuente++;
                    pesoActual += c.getPeso();
                }

                System.out.println("Coche de categoria" + c.getCategoria() + " cruzando el puente...");

                Thread.sleep((int)(Math.random()*500 + 500));

                synchronized (this) {
                    cochesEnPuente--;
                    pesoActual -= c.getPeso();
                    System.out.println("Coche de categoria " + c.getCategoria() + " ha salido del puente.");
                    notifyAll();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}