package EjEntrega;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Actividad4Borrado {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Uso: java BorrarEmpleado <id>");
            return;
        }

        int idBuscado = Integer.parseInt(args[0]);
        String fichero = "empleados.dat";
        boolean encontrado = false;

        try (RandomAccessFile raf = new RandomAccessFile(fichero, "rw")) {
            long numRegistros = raf.length() / 36;

            for (int i = 0; i < numRegistros; i++) {
                raf.seek(i * 36);
                int id = raf.readInt();

                if (id == idBuscado) {
                    // Posicionarse al inicio del registro
                    raf.seek(i * 36);
                    raf.writeInt(-1);

                    StringBuffer buffer = new StringBuffer(String.valueOf(idBuscado));
                    buffer.setLength(10);
                    raf.writeChars(buffer.toString());

                    raf.writeInt(0);
                    raf.writeDouble(0.0);

                    System.out.println("Empleado con ID " + idBuscado + " borrado lógicamente.");
                    encontrado = true;
                    break;
                }
            }

            if (!encontrado) {
                System.out.println("No se ha encontrado el empleado con ID " + idBuscado);
            }

        } catch (IOException e) {
            System.out.println("Error al borrar el fichero: " + e.getMessage());
        }
    }
}
