package ejercicio1Sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/*
 * psp 2025
 * Clase cliente, establece una conexion con el servidor TCP
 * le envia un mensaje con su IP y puerto
 * lee el mensaje de eco que le envia el servidor 
 */
public class Cliente {
    public static void main(String[] args) {

        final String HOST = "localhost";
        final int PUERTO = 500;

        try {
            Socket socket = new Socket(HOST, PUERTO);
            System.out.println("Conectado al servidor.");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // ------- HILO LECTOR (lee mensajes del servidor) -------
            Thread lector = new Thread(() -> {
                try {
                    String mensaje;
                    while ((mensaje = in.readLine()) != null) {
                        System.out.println("Servidor: " + mensaje);
                    }
                } catch (IOException e) {
                    System.out.println("Servidor desconectado.");
                }
            });

            // ------- HILO ESCRITOR (envía mensajes desde consola) -------
            Thread escritor = new Thread(() -> {
                Scanner sc = new Scanner(System.in);
                while (true) {
                    String msg = sc.nextLine();
                    out.println(msg);
                }
            });

            lector.start();
            escritor.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}