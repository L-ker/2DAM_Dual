package EjEntrega;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Actividad2 {
    public static void main(String[] args) {
        // Comprobar si se ha pasado un nombre de fichero
        if (args.length == 0) {
            System.out.println("Uso: java LeerFichero <nombre_fichero>");
            return;
        }

        String nombreFichero = args[0];
        File fichero = new File(nombreFichero);

        // Comprobamos si el fichero existe
        if (!fichero.exists()) {
            System.out.println("El fichero no existe: " + nombreFichero);
            return;
        }

        // Comprobamos si es un fichero (no un directorio)
        if (!fichero.isFile()) {
            System.out.println("La ruta no corresponde a un fichero: " + nombreFichero);
            return;
        }

        // Leemos el contenido del fichero línea por línea
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;
            System.out.println("Contenido del fichero '" + fichero.getName() + "':\n");
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }
}
