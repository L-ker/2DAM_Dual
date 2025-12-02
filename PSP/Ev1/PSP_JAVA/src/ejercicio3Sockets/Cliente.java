package ejercicio3Sockets;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {

        final String HOST = "localhost";
        final int PUERTO = 500;

        try (Socket socket = new Socket(HOST, PUERTO);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             Scanner sc = new Scanner(System.in)) {

            System.out.println(in.readLine()); // mensaje inicial del servidor

            System.out.println("Introduce tu jugada (piedra, papel o tijera): ");
            String jugada = sc.nextLine().toLowerCase();

            // Validación simple
            while (!jugada.matches("piedra|papel|tijera")) {
                System.out.println("Jugada inválida. Escribe piedra, papel o tijera:");
                jugada = sc.nextLine().toLowerCase();
            }

            out.println(jugada);

            // Leer resultado final
            String respuesta = in.readLine();
            System.out.println(respuesta);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
