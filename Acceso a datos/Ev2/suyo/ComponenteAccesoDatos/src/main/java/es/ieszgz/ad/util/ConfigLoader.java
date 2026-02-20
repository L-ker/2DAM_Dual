package es.ieszgz.ad.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Utilidad para cargar configuración desde archivos properties.
 * 
 * <p>Carga el archivo <code>config.properties</code> desde
 * el classpath (src/main/resources) y proporciona acceso
 * a sus valores.</p>
 * 
 * <h2>Archivo config.properties esperado:</h2>
 * <pre>
 * # Base de datos activa
 * db.active=mysql
 * 
 * # MySQL
 * mysql.url=jdbc:mysql://localhost:3306/tienda
 * mysql.user=root
 * mysql.password=secret
 * 
 * # MongoDB
 * mongo.uri=mongodb://localhost:27017
 * mongo.database=tienda
 * mongo.collection=productos
 * </pre>
 * 
 * @author CPIFP Los Enlaces
 * @version 1.0
 * @since 2024
 */
public class ConfigLoader {
    
    /** Nombre del archivo de configuración */
    private static final String CONFIG_FILE = "config.properties";
    
    /** Propiedades cargadas */
    private static Properties props = null;
    
    /** Indica si hubo error al cargar */
    private static boolean errorCarga = false;
    
    // Bloque estático: se ejecuta una sola vez al cargar la clase
    static {
        cargarPropiedades();
    }
    
    /**
     * Carga las propiedades desde el archivo de configuración.
     */
    private static void cargarPropiedades() {
        props = new Properties();
        
        try (InputStream is = ConfigLoader.class
                .getClassLoader()
                .getResourceAsStream(CONFIG_FILE)) {
            
            if (is == null) {
                System.err.println("═══════════════════════════════════════════════════════════");
                System.err.println("  ❌ ERROR: No se encuentra " + CONFIG_FILE);
                System.err.println("  📁 Verifica que esté en src/main/resources/");
                System.err.println("═══════════════════════════════════════════════════════════");
                errorCarga = true;
                return;
            }
            
            props.load(is);
            
        } catch (IOException e) {
            System.err.println("❌ Error al cargar configuración: " + e.getMessage());
            errorCarga = true;
        }
    }
    
    /**
     * Obtiene el valor de una propiedad.
     * 
     * @param key nombre de la propiedad
     * @return valor de la propiedad o null si no existe
     */
    public static String get(String key) {
        if (errorCarga || props == null) {
            return null;
        }
        return props.getProperty(key);
    }
    
    /**
     * Obtiene el valor de una propiedad con valor por defecto.
     * 
     * @param key nombre de la propiedad
     * @param defaultValue valor por defecto si no existe
     * @return valor de la propiedad o el valor por defecto
     */
    public static String get(String key, String defaultValue) {
        if (errorCarga || props == null) {
            return defaultValue;
        }
        return props.getProperty(key, defaultValue);
    }
    
    /**
     * Obtiene un valor entero de una propiedad.
     * 
     * @param key nombre de la propiedad
     * @param defaultValue valor por defecto
     * @return valor entero o el valor por defecto
     */
    public static int getInt(String key, int defaultValue) {
        String value = get(key);
        if (value == null) return defaultValue;
        
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
    
    /**
     * Obtiene un valor booleano de una propiedad.
     * 
     * @param key nombre de la propiedad
     * @param defaultValue valor por defecto
     * @return valor booleano o el valor por defecto
     */
    public static boolean getBoolean(String key, boolean defaultValue) {
        String value = get(key);
        if (value == null) return defaultValue;
        
        return Boolean.parseBoolean(value.trim());
    }
    
    /**
     * Verifica si la configuración se cargó correctamente.
     * 
     * @return true si no hubo errores
     */
    public static boolean isLoaded() {
        return !errorCarga && props != null;
    }
    
    /**
     * Recarga la configuración desde el archivo.
     * Útil si se modificó el archivo en tiempo de ejecución.
     */
    public static void reload() {
        errorCarga = false;
        props = null;
        cargarPropiedades();
    }
    
    /**
     * Imprime todas las propiedades cargadas (para debug).
     * Oculta valores sensibles como passwords.
     */
    public static void debug() {
        System.out.println("╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║                    CONFIGURACIÓN CARGADA                  ║");
        System.out.println("╠═══════════════════════════════════════════════════════════╣");
        
        if (errorCarga || props == null) {
            System.out.println("║  ⚠️  No hay configuración cargada                        ║");
        } else {
            for (String key : props.stringPropertyNames()) {
                String value = props.getProperty(key);
                // Ocultar passwords
                if (key.toLowerCase().contains("password") || 
                    key.toLowerCase().contains("pass") ||
                    key.toLowerCase().contains("secret")) {
                    value = "********";
                }
                System.out.printf("║  %-20s = %-34s ║%n", key, value);
            }
        }
        
        System.out.println("╚═══════════════════════════════════════════════════════════╝");
    }
}
