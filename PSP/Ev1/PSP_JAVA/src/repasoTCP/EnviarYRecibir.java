package repasoTCP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class EnviarYRecibir {

    public static void main(String[] args) {
        final String HOST = "localhost";
        final int PUERTO = 500;

        try (Socket socket = new Socket(HOST, PUERTO);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner sc = new Scanner(System.in)) {

            System.out.println("Conectado al servidor en " + HOST + ":" + PUERTO);

            // Hilo para recibir mensajes del servidor
            Thread receptor = new Thread(() -> {
                try {
                    String mensajeServidor;
                    while ((mensajeServidor = in.readLine()) != null) {
                        System.out.println("\nServidor: " + mensajeServidor);
                        System.out.print("Tú: ");
                    }
                } catch (Exception e) {
                    System.out.println("Conexión cerrada.");
                }
            });
            receptor.start();

            // Hilo principal para enviar mensajes
            String mensaje;
            while (true) {
                System.out.print("Tú: ");
                mensaje = sc.nextLine();

                if (mensaje.equalsIgnoreCase("salir")) {
                    System.out.println("Cerrando cliente...");
                    break;
                }

                out.println(mensaje);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
