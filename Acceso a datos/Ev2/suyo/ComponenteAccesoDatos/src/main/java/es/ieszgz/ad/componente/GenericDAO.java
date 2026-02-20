package es.ieszgz.ad.componente;

import java.util.List;

/**
 * Interfaz genérica para componentes de acceso a datos (DAO).
 * 
 * <p>Define las operaciones CRUD básicas que todo componente
 * de acceso a datos debe implementar, independientemente
 * del sistema de persistencia subyacente.</p>
 * 
 * <h2>Uso típico:</h2>
 * <pre>{@code
 * GenericDAO<Producto, String> dao = new ProductoDAOMongo();
 * dao.conectar();
 * List<Producto> productos = dao.buscarTodos();
 * dao.desconectar();
 * }</pre>
 * 
 * @param <T> Tipo de la entidad (ej: Producto, Cliente)
 * @param <ID> Tipo del identificador (ej: Integer, String)
 * 
 * @author CPIFP Los Enlaces
 * @version 1.0
 * @since 2024
 */
public interface GenericDAO<T, ID> {
    
    // ═══════════════════════════════════════════════════════════════════
    // CONEXIÓN
    // ═══════════════════════════════════════════════════════════════════
    
    /**
     * Establece la conexión con la base de datos.
     * 
     * @throws Exception si no se puede establecer la conexión
     */
    void conectar() throws Exception;
    
    /**
     * Cierra la conexión con la base de datos.
     */
    void desconectar();
    
    /**
     * Verifica si hay una conexión activa.
     * 
     * @return true si está conectado
     */
    boolean estaConectado();
    
    // ═══════════════════════════════════════════════════════════════════
    // CRUD - CREATE
    // ═══════════════════════════════════════════════════════════════════
    
    /**
     * Inserta una nueva entidad en la base de datos.
     * 
     * @param entidad objeto a insertar
     */
    void insertar(T entidad);
    
    // ═══════════════════════════════════════════════════════════════════
    // CRUD - READ
    // ═══════════════════════════════════════════════════════════════════
    
    /**
     * Busca una entidad por su identificador.
     * 
     * @param id identificador de la entidad
     * @return la entidad encontrada o null si no existe
     */
    T buscarPorId(ID id);
    
    /**
     * Obtiene todas las entidades de la colección/tabla.
     * 
     * @return lista de todas las entidades
     */
    List<T> buscarTodos();
    
    /**
     * Cuenta el número total de entidades.
     * 
     * @return número de registros
     */
    long contar();
    
    // ═══════════════════════════════════════════════════════════════════
    // CRUD - UPDATE
    // ═══════════════════════════════════════════════════════════════════
    
    /**
     * Actualiza una entidad existente.
     * 
     * @param entidad objeto con los datos actualizados
     */
    void actualizar(T entidad);
    
    // ═══════════════════════════════════════════════════════════════════
    // CRUD - DELETE
    // ═══════════════════════════════════════════════════════════════════
    
    /**
     * Elimina una entidad por su identificador.
     * 
     * @param id identificador de la entidad a eliminar
     */
    void eliminar(ID id);
    
    /**
     * Elimina todas las entidades de la colección/tabla.
     * 
     * @return número de registros eliminados
     */
    long eliminarTodos();
}
