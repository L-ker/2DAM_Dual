package repasoUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Servidor {
	public static void main(String[] args) {
        final int PUERTO = 500; // puerto en el que va a estar escuchando
        try (DatagramSocket socket = new DatagramSocket(PUERTO)) {// Creo el socket UDP
            System.out.println("Servidor escuchando en puerto " + PUERTO);
            // Estructura para almacenar el contenoido del datagrama
            byte[] buffer = new byte[1024];

            // estaremos siempre a la escucha
            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, PUERTO); //
                socket.receive(packet); // leo el Datagrama y lo almaceno en packet. quda boqueado hasta recibir un
                                        // datagrama

                // Extraigo el mensaje y lo imprimo
                String message = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Recibido de " + packet.getAddress() + ":" + packet.getPort() + " -> " + message);

                // Enviamos un datagreama con la respuesta
                /*
                 * DatagramPacket packet = new DatagramPacket(datos, datos.length, destino,
                 * puerto);
                 * datos → contenido que quieres enviar
                 * 
                 * datos.length → longitud del mensaje
                 * 
                 * destino → IP del receptor
                 * 
                 * puerto → puerto del receptor
                 */
                // Reenviar el mismo mensaje al cliente (eco)
                DatagramPacket response = new DatagramPacket(
                        packet.getData(),
                        packet.getLength(),
                        packet.getAddress(),
                        packet.getPort());
                socket.send(response);
            }

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}