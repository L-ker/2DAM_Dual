package TareaCochesPuenteV2;

import java.util.concurrent.LinkedBlockingQueue;

public class Coche extends Thread {
    private final String categoria;
    private final int peso;
    private final Puente puente;

    public Coche(String categoria, Puente puente) {
        this.categoria = categoria;
        this.peso = switch(categoria) {
        case "A" -> 100;
        case "B" -> 200;
        case "C" -> 300;
        default -> 0;
    };
        this.puente = puente;
    }

    @Override
    public void run() {
        puente.entrar(this);
        try {
            Thread.sleep((int)(Math.random() * 2000) + 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        puente.salir(this);
    }

    public String getCategoria() { return categoria; }
    public int getPeso() { return peso; }
}
