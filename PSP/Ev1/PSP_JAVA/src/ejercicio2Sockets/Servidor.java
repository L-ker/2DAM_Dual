package ejercicio2Sockets;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

public class Servidor {

    // Contador global de clientes que han conseguido oferta
    private static AtomicInteger contadorClientes = new AtomicInteger(0);

    public static void main(String[] args) {
        final int PUERTO = 500;

        try (ServerSocket server = new ServerSocket(PUERTO)) {
            System.out.println("Servidor Black Friday escuchando en el puerto " + PUERTO);

            while (true) {
                Socket socket = server.accept();
                new Thread(() -> manejarCliente(socket)).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void manejarCliente(Socket socket) {

        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            String nombre = in.readLine(); // El cliente envía su nombre

            int posicion = contadorClientes.incrementAndGet();  // cliente nº X

            if (posicion <= 10) {
                int descuento = 95 - (posicion * 5);  // genera 90, 85, 80, ..., 45

                out.println("Hola " + nombre +
                            ", eres el cliente nº " + posicion +
                            " y tu descuento es del " + descuento + "%");
                System.out.println("Asignado a " + nombre +
                                   ": pos " + posicion +
                                   ", desc = " + descuento + "%");

            } else {
                out.println("Hola " + nombre +
                            ", ya no quedan descuentos disponibles. Posición " + posicion);
                System.out.println(nombre + " se conectó sin oferta. Posición "+posicion);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
