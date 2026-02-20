package es.ieszgz.ad.componente;

import es.ieszgz.ad.modelo.Producto;
import java.util.List;

/**
 * Interfaz específica para acceso a datos de Productos.
 * 
 * <p>Extiende {@link GenericDAO} añadiendo operaciones
 * específicas para la gestión de productos.</p>
 * 
 * @author CPIFP Los Enlaces
 * @version 1.0
 * @since 2024
 */
public interface ProductoDAO extends GenericDAO<Producto, String> {
    
    /**
     * Busca productos por categoría.
     * 
     * @param categoria nombre de la categoría
     * @return lista de productos de esa categoría
     */
    List<Producto> buscarPorCategoria(String categoria);
    
    /**
     * Busca productos con precio en un rango.
     * 
     * @param precioMin precio mínimo (inclusive)
     * @param precioMax precio máximo (inclusive)
     * @return lista de productos en ese rango de precios
     */
    List<Producto> buscarPorRangoPrecio(double precioMin, double precioMax);
    
    /**
     * Busca productos con stock disponible.
     * 
     * @return lista de productos con stock > 0
     */
    List<Producto> buscarConStock();
    
    /**
     * Busca productos sin stock (agotados).
     * 
     * @return lista de productos con stock = 0
     */
    List<Producto> buscarSinStock();
    
    /**
     * Busca productos cuyo nombre contenga un texto.
     * 
     * @param texto texto a buscar (case insensitive)
     * @return lista de productos que coinciden
     */
    List<Producto> buscarPorNombre(String texto);
    
    /**
     * Actualiza el stock de un producto.
     * 
     * @param id identificador del producto
     * @param nuevoStock nueva cantidad de stock
     */
    void actualizarStock(String id, int nuevoStock);
    
    /**
     * Obtiene los productos más caros.
     * 
     * @param limite número máximo de productos a devolver
     * @return lista de productos ordenados por precio descendente
     */
    List<Producto> obtenerMasCaros(int limite);
}
