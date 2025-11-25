package ejercicio2Sockets;

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

            System.out.print("Introduce tu nombre: ");
            String nombre = sc.nextLine();

            out.println(nombre);  // Enviar nombre

            String respuesta = in.readLine(); // Leer respuesta del servidor
            System.out.println("Servidor: " + respuesta);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
