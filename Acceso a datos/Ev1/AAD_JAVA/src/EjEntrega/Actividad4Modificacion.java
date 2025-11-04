package EjEntrega;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Actividad4Modificacion {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Uso: java ModificarEmpleado <id> <importe>");
            return;
        }

        int idBuscado = Integer.parseInt(args[0]);
        double importe = Double.parseDouble(args[1]);
        String fichero = "empleados.dat";
        boolean encontrado = false;

        try (RandomAccessFile raf = new RandomAccessFile(fichero, "rw")) {
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
                    double nuevoSalario = salario + importe;

                    // Volver a la posición del salario
                    raf.seek(i * 36 + 4 + 20 + 4);
                    raf.writeDouble(nuevoSalario);

                    System.out.println("Empleado encontrado:");
                    System.out.println("Apellido: " + apellido);
                    System.out.println("Salario antiguo: " + salario);
                    System.out.println("Salario nuevo: " + nuevoSalario);
                    encontrado = true;
                    break;
                }
            }

            if (!encontrado) {
                System.out.println("No se ha encontrado el empleado con ID " + idBuscado);
            }

        } catch (IOException e) {
            System.out.println("Error al modificar el fichero: " + e.getMessage());
        }
    }
}
