package es.ieszgz.ad.factory;

import es.ieszgz.ad.componente.ProductoDAO;
import es.ieszgz.ad.mongo.ProductoDAOMongo;
import es.ieszgz.ad.mysql.ProductoDAOMySQL;
import es.ieszgz.ad.util.ConfigLoader;

/**
 * Factoría para crear instancias de componentes DAO.
 * 
 * <p>Implementa el patrón Factory Method para desacoplar
 * la creación de objetos DAO de su uso en la aplicación.</p>
 * 
 * <h2>Ventajas:</h2>
 * <ul>
 *   <li>Cambiar de BD modificando solo config.properties</li>
 *   <li>Código cliente independiente de implementaciones concretas</li>
 *   <li>Facilita testing con mocks</li>
 * </ul>
 * 
 * <h2>Configuración en config.properties:</h2>
 * <pre>
 * db.active=mysql   # o "mongo"
 * </pre>
 * 
 * <h2>Uso:</h2>
 * <pre>{@code
 * ProductoDAO dao = DAOFactory.crearProductoDAO();
 * // o
 * ProductoDAO dao = DAOFactory.crearProductoDAO(TipoBD.MONGODB);
 * }</pre>
 * 
 * @author CPIFP Los Enlaces
 * @version 1.0
 * @since 2024
 */
public class DAOFactory {
    
    /**
     * Tipos de base de datos soportados.
     */
    public enum TipoBD {
        MYSQL,
        MONGODB
    }
    
    /**
     * Crea un ProductoDAO según la configuración del archivo properties.
     * Lee la propiedad "db.active" para determinar qué implementación usar.
     * 
     * @return instancia de ProductoDAO
     * @throws IllegalArgumentException si el tipo no está soportado
     */
    public static ProductoDAO crearProductoDAO() {
        String tipoActivo = ConfigLoader.get("db.active", "mysql").toLowerCase();
        
        switch (tipoActivo) {
            case "mysql":
                return new ProductoDAOMySQL();
            case "mongo":
            case "mongodb":
                return new ProductoDAOMongo();
            default:
                throw new IllegalArgumentException(
                        "Tipo de BD no soportado: " + tipoActivo + 
                        ". Valores válidos: mysql, mongo");
        }
    }
    
    /**
     * Crea un ProductoDAO del tipo especificado.
     * 
     * @param tipo tipo de base de datos
     * @return instancia de ProductoDAO
     * @throws IllegalArgumentException si el tipo es null
     */
    public static ProductoDAO crearProductoDAO(TipoBD tipo) {
        if (tipo == null) {
            throw new IllegalArgumentException("El tipo de BD no puede ser null");
        }
        
        switch (tipo) {
            case MYSQL:
                return new ProductoDAOMySQL();
            case MONGODB:
                return new ProductoDAOMongo();
            default:
                // No debería ocurrir con un enum, pero por seguridad
                throw new IllegalArgumentException("Tipo no implementado: " + tipo);
        }
    }
    
    /**
     * Obtiene información sobre el tipo de BD activo.
     * 
     * @return nombre del tipo de BD configurado
     */
    public static String getTipoActivo() {
        return ConfigLoader.get("db.active", "mysql");
    }
    
    /**
     * Verifica si un tipo de BD está disponible.
     * 
     * @param tipo tipo a verificar
     * @return true si el tipo está soportado
     */
    public static boolean esTipoSoportado(String tipo) {
        if (tipo == null) return false;
        String t = tipo.toLowerCase();
        return t.equals("mysql") || t.equals("mongo") || t.equals("mongodb");
    }
}
