package ejercicio1Sockets;

/*
 * psp 2025
 * programa servidor 
 * Crea un Serversocket TCP
 * Acepta conexiones de clients gestionadas por hilos
 * Escribe por consola el mensaje recibido y quien lo manda
 * y reenvia al cliente el mismo mensaje
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor {

    public static void main(String[] args) {
        final int PUERTO = 500;

        try (ServerSocket server = new ServerSocket(PUERTO)) {
            System.out.println("Servidor escuchando en el puerto " + PUERTO);

            while (true) {
                Socket socket = server.accept();
                System.out.println("Cliente conectado: " + socket.getInetAddress());

                new Thread(() -> manejarCliente(socket)).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void manejarCliente(Socket socket) {

        try {
            // Streams del socket
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // --------- HILO LECTOR (recibe mensajes del cliente) ----------
            Thread lector = new Thread(() -> {
                try {
                    String mensaje;
                    while ((mensaje = in.readLine()) != null) {
                        System.out.println("Cliente dice: " + mensaje);
                    }
                } catch (IOException e) {
                    System.out.println("Cliente desconectado.");
                }
            });

            // --------- HILO ESCRITOR (envía mensajes desde consola) ----------
            Thread escritor = new Thread(() -> {
                Scanner sc = new Scanner(System.in);
                while (true) {
                    String msg = sc.nextLine();
                    out.println(msg);
                }
            });

            lector.start();
            escritor.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}