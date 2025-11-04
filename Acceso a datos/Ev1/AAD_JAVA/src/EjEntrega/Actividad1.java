package EjEntrega;

import java.io.File;

public class Actividad1 {
    public static void main(String[] args) {
        // Si no se pasa ningún argumento, usar el directorio actual
        String rutaDirectorio;
        if (args.length > 0) {
            rutaDirectorio = args[0];
        } else {
            rutaDirectorio = ".";
        }

        File directorio = new File(rutaDirectorio);

        // Comprobamos si el directorio existe y es un directorio válido
        if (!directorio.exists()) {
            System.out.println("El directorio no existe: " + rutaDirectorio);
            return;
        }

        if (!directorio.isDirectory()) {
            System.out.println("La ruta no corresponde a un directorio: " + rutaDirectorio);
            return;
        }

        // Obtenemos la lista de ficheros
        File[] archivos = directorio.listFiles();

        if (archivos == null || archivos.length == 0) {
            System.out.println("El directorio está vacío.");
        } else {
            System.out.println("Contenido del directorio: " + directorio.getAbsolutePath());
            for (File archivo : archivos) {
                if (archivo.isDirectory()) {
                    System.out.println("[DIR]  " + archivo.getName());
                } else {
                    System.out.println("[FILE] " + archivo.getName());
                }
            }
        }
    }
}
