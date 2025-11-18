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

    public static void main(String [] args){

        //Puerto que el servidor usa para escuchar
        final int PUERTO=500;

        //Creamos el serverSocket
        //Al ponerlo dentro del try se cierra automáticamente
        try(ServerSocket server=new ServerSocket(PUERTO)){
            System.out.println("Servidor escuchando en el puerto "+ PUERTO);

            //Estamos constantemente aceptando conexiones
            while(true){
                /*
                 * El programa se queda parado hasta que recibe una conexión.
                 * Una vez establecida crea un nuevo soxket para la comunicación
                 * cliente-servidor
                 */
                Socket socket=server.accept();
                //imprimo la ip desde que se ha conectado
                System.out.println("Cliente conectado"+socket.getInetAddress());

                //manejo el cliente con un hilo alq ue le paso el Socket
                new Thread (()->leerCliente(socket)).start();
                new Thread (()->escribirCliente(socket)).start();

            }

            }catch(IOException e){
                e.printStackTrace();
            }

            
        }



    private static void leerCliente(Socket socket) {
        System.err.println("Hilo de lectura");
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String mensaje;
            while ((mensaje = in.readLine()) != null) {
                System.out.println("Cliente dice: " + mensaje + " desde " + socket.getInetAddress());
            }
        } catch (IOException e) {
            System.out.println("Cliente desconectado: " + socket.getInetAddress());
        }
    }

    private static void escribirCliente(Socket socket) {
        System.err.println("Hilo de escritura");
        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             Scanner teclado = new Scanner(System.in)) {
            while (true) {
                System.out.print("Servidor escribe: ");
                String mensaje = teclado.nextLine();
                out.println(mensaje);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
