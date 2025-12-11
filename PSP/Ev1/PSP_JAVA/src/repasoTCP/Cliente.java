package repasoTCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {
	public static void main(String[] args) {
        // IP y puerto del servidor al que nos conectamos
        final String HOST = "localhost";
        final int PUERTO = 500;

        // nos conectamos al servidor
        // otro trywithresources con todo lo que se debe cerrar
        // Socket de conexion y BufferedReader y PrintWriter para leer y escribir
        try (Socket socket = new Socket(HOST, PUERTO);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(),true);) {
            System.out.println("Conectado al servidor en IP: " + socket.getInetAddress());
            System.out.println("En el puerto: " + socket.getPort());
            System.out.println("usando el puerto " + socket.getLocalPort());
            System.out.println("Desde la dirección " + socket.getLocalAddress());
            String mensaje = "Hola soy Juanjo";
            // Escribo en le socket
            out.println(mensaje);
            // Leo la respuesta solo una para cerrar el socket
            // Leer UNA sola respuesta
            String respuesta = in.readLine();
            System.out.println("Respuesta del servidor: " + respuesta);
            //el try cierra todo

        } catch (UnknownHostException e) {
            
            e.printStackTrace();
        } catch (IOException e) {
            
            e.printStackTrace();
        }

    }

}