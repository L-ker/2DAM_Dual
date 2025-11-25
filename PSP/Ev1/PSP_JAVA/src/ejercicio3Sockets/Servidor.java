package ejercicio3Sockets;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) {
        final int PUERTO = 500;

        try (ServerSocket server = new ServerSocket(PUERTO)) {
            System.out.println("Servidor esperando jugadores...");

            // Aceptar a los dos jugadores
            Socket jugador1 = server.accept();
            System.out.println("Jugador 1 conectado.");

            Socket jugador2 = server.accept();
            System.out.println("Jugador 2 conectado.");

            // Crear canales de comunicación
            BufferedReader in1 = new BufferedReader(new InputStreamReader(jugador1.getInputStream()));
            PrintWriter out1 = new PrintWriter(jugador1.getOutputStream(), true);

            BufferedReader in2 = new BufferedReader(new InputStreamReader(jugador2.getInputStream()));
            PrintWriter out2 = new PrintWriter(jugador2.getOutputStream(), true);

            out1.println("Conectado. Esperando jugada del otro jugador...");
            out2.println("Conectado. Esperando jugada del otro jugador...");

            // Recoger jugadas
            String jugada1 = in1.readLine();
            String jugada2 = in2.readLine();

            System.out.println("Jugador 1: " + jugada1);
            System.out.println("Jugador 2: " + jugada2);

            String resultado = determinarGanador(jugada1, jugada2);

            // Enviar resultado a ambos
            out1.println("Resultado: " + resultado);
            out2.println("Resultado: " + resultado);

            jugador1.close();
            jugador2.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para determinar ganador
    private static String determinarGanador(String j1, String j2) {

        if (j1.equals(j2)) {
            return "Empate";
        }

        if ((j1.equals("piedra") && j2.equals("tijera")) ||
            (j1.equals("papel") && j2.equals("piedra")) ||
            (j1.equals("tijera") && j2.equals("papel"))) {
            return "Gana Jugador 1";
        }

        return "Gana Jugador 2";
    }
}
