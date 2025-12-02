package testSockets;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class testMandarYRecibir2 {

    public static void main(String[] args) {

        final String HOST = "172.16.24.178";
        final int PUERTO = 500;
        final int PUERTO_DESTINO = 500;

        try (DatagramSocket socket = new DatagramSocket(PUERTO);
             Scanner scanner = new Scanner(System.in)) {

            InetAddress address = InetAddress.getByName(HOST);

            System.out.println("Escribe mensajes para enviar:");
            System.out.println("('salir' para cerrar)");


            Thread receptor = new Thread(() -> {
                try {
                	byte[] buffer = new byte[1024];
                    while (true) {
                        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

                        socket.receive(packet); 

                        String msg = new String(packet.getData(), 0, packet.getLength());
                        System.out.println();
                        System.out.println("Servidor dice: " + msg);
                    }
                } catch (IOException e) {
                    System.out.println("Receptor cerrado.");
                }
            });

            receptor.start();
            while (true) {
                System.out.print("Tú: ");
                String mensaje = scanner.nextLine();

                if (mensaje.equalsIgnoreCase("salir")) {
                    System.out.println("Cerrando cliente...");
                    socket.close();
                    break;
                }

                byte[] buffer = mensaje.getBytes();
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, PUERTO_DESTINO);

                socket.send(packet);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
