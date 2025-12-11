package SemaforoCajera;

/*
 * Clase cajera
 * Atiende al cliente
 * Tiene un nombre y un semaforo
 */
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Cajera {
    private final String nombre;
    private final Semaphore semaforo = new Semaphore(1); // solo 1 cliente a la vez

    public Cajera(String nombre) {
        this.nombre = nombre;
    }

    public void atenderCliente(String cliente) {
        try {
            semaforo.acquire(); // Espera si la cajera está ocupada
            System.out.println(cliente + " está siendo atendido por " + nombre);
            // Simulamos tiempo de atención
            Thread.sleep(new Random().nextInt(2000) + 1000);
            System.out.println(nombre + " terminó de atender a " + cliente);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaforo.release(); // Libera la cajera
        }
    }

    public String getNombre() {
        return nombre;
    }

}