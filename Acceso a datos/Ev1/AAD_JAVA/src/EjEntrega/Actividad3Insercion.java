package EjEntrega;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Actividad3Insercion {
    public static void main(String[] args) {
        if (args.length != 4) {
            System.out.println("Uso: java InsertarEmpleado <id> <apellido> <departamento> <salario>");
            return;
        }

        int id = Integer.parseInt(args[0]);
        String apellido = args[1];
        int departamento = Integer.parseInt(args[2]);
        double salario = Double.parseDouble(args[3]);

        String fichero = "empleados.dat";

        try (RandomAccessFile raf = new RandomAccessFile(fichero, "rw")) {
            long numRegistros = raf.length() / 36;
            boolean existe = false;

            // Buscar si ya existe el empleado
            for (int i = 0; i < numRegistros; i++) {
                raf.seek(i * 36);
                int idLeido = raf.readInt();
                if (idLeido == id) {
                    existe = true;
                    break;
                }
            }

            if (existe) {
                System.out.println("El empleado con ID " + id + " ya existe.");
                return;
            }

            // Mover al final para escribir nuevo registro
            raf.seek(raf.length());
            raf.writeInt(id);

            // Apellido con longitud fija de 10 caracteres
            StringBuffer buffer = new StringBuffer(apellido);
            buffer.setLength(10);
            raf.writeChars(buffer.toString());

            raf.writeInt(departamento);
            raf.writeDouble(salario);

            System.out.println("Empleado insertado correctamente.");

        } catch (IOException e) {
            System.out.println("Error al acceder al fichero: " + e.getMessage());
        }
    }
}
