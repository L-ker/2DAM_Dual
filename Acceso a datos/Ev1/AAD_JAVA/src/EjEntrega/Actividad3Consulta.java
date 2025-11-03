package EjEntrega;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Actividad3Consulta {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Uso: java ConsultarEmpleado <id>");
            return;
        }

        int idBuscado = Integer.parseInt(args[0]);
        String fichero = "empleados.dat";
        boolean encontrado = false;

        try (RandomAccessFile raf = new RandomAccessFile(fichero, "r")) {
            long numRegistros = raf.length() / 36;

            for (int i = 0; i < numRegistros; i++) {
                raf.seek(i * 36);
                int id = raf.readInt();

                char[] apellidoChars = new char[10];
                for (int j = 0; j < 10; j++) {
                    apellidoChars[j] = raf.readChar();
                }
                String apellido = new String(apellidoChars).trim();

                int departamento = raf.readInt();
                double salario = raf.readDouble();

                if (id == idBuscado) {
                    System.out.println("Empleado encontrado:");
                    System.out.println("ID: " + id);
                    System.out.println("Apellido: " + apellido);
                    System.out.println("Departamento: " + departamento);
                    System.out.println("Salario: " + salario);
                    encontrado = true;
                    break;
                }
            }

            if (!encontrado) {
                System.out.println("No se ha encontrado el empleado con ID " + idBuscado);
            }

        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }
}
