package es.ieszgz.ad;

import es.ieszgz.ad.componente.ProductoDAO;
import es.ieszgz.ad.factory.DAOFactory;
import es.ieszgz.ad.modelo.Producto;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitarios para el componente ProductoDAO.
 * 
 * <p>Estos tests verifican el correcto funcionamiento
 * de las operaciones CRUD del componente.</p>
 * 
 * <p><b>IMPORTANTE:</b> Requiere una BD activa y configurada
 * en config.properties para ejecutarse.</p>
 * 
 * @author CPIFP Los Enlaces
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Tests del Componente ProductoDAO")
public class ProductoDAOTest {
    
    private ProductoDAO dao;
    private String idProductoTest;
    
    @BeforeAll
    void setUp() throws Exception {
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("  INICIANDO TESTS DEL COMPONENTE");
        System.out.println("  BD activa: " + DAOFactory.getTipoActivo());
        System.out.println("═══════════════════════════════════════════════════════════");
        
        dao = DAOFactory.crearProductoDAO();
        dao.conectar();
        
        // Limpiar datos previos de test
        dao.eliminarTodos();
    }
    
    @AfterAll
    void tearDown() {
        if (dao != null) {
            // Limpiar datos de test
            dao.eliminarTodos();
            dao.desconectar();
        }
        
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("  TESTS FINALIZADOS");
        System.out.println("═══════════════════════════════════════════════════════════");
    }
    
    // ═══════════════════════════════════════════════════════════════════════════
    // TESTS DE CONEXIÓN
    // ═══════════════════════════════════════════════════════════════════════════
    
    @Test
    @Order(1)
    @DisplayName("Verificar conexión activa")
    void testConexionActiva() {
        assertTrue(dao.estaConectado(), "La conexión debería estar activa");
    }
    
    // ═══════════════════════════════════════════════════════════════════════════
    // TESTS DE INSERT
    // ═══════════════════════════════════════════════════════════════════════════
    
    @Test
    @Order(2)
    @DisplayName("Insertar producto nuevo")
    void testInsertarProducto() {
        Producto p = new Producto("Test Laptop", 999.99, 10, "test");
        
        dao.insertar(p);
        
        // El ID debería estar asignado después de insertar
        assertNotNull(p.getId(), "El ID debería asignarse tras insertar");
        idProductoTest = p.getId();
        
        // Verificar que se puede recuperar
        Producto recuperado = dao.buscarPorId(idProductoTest);
        assertNotNull(recuperado, "El producto debería existir");
        assertEquals("Test Laptop", recuperado.getNombre());
    }
    
    @Test
    @Order(3)
    @DisplayName("Insertar múltiples productos")
    void testInsertarMultiples() {
        long countAntes = dao.contar();
        
        dao.insertar(new Producto("Producto A", 10.0, 5, "categoria1"));
        dao.insertar(new Producto("Producto B", 20.0, 10, "categoria1"));
        dao.insertar(new Producto("Producto C", 30.0, 0, "categoria2"));
        
        long countDespues = dao.contar();
        
        assertEquals(countAntes + 3, countDespues, "Deberían haberse insertado 3 productos");
    }
    
    // ═══════════════════════════════════════════════════════════════════════════
    // TESTS DE SELECT
    // ═══════════════════════════════════════════════════════════════════════════
    
    @Test
    @Order(4)
    @DisplayName("Buscar todos los productos")
    void testBuscarTodos() {
        List<Producto> productos = dao.buscarTodos();
        
        assertNotNull(productos);
        assertFalse(productos.isEmpty(), "Debería haber productos");
        assertTrue(productos.size() >= 4, "Debería haber al menos 4 productos");
    }
    
    @Test
    @Order(5)
    @DisplayName("Buscar por ID existente")
    void testBuscarPorIdExistente() {
        assertNotNull(idProductoTest, "Debería existir un ID de test");
        
        Producto p = dao.buscarPorId(idProductoTest);
        
        assertNotNull(p, "Debería encontrar el producto");
        assertEquals(idProductoTest, p.getId());
    }
    
    @Test
    @Order(6)
    @DisplayName("Buscar por ID inexistente devuelve null")
    void testBuscarPorIdInexistente() {
        Producto p = dao.buscarPorId("000000000000000000000000"); // ID inválido
        assertNull(p, "Debería devolver null para ID inexistente");
    }
    
    @Test
    @Order(7)
    @DisplayName("Buscar por categoría")
    void testBuscarPorCategoria() {
        List<Producto> productos = dao.buscarPorCategoria("categoria1");
        
        assertNotNull(productos);
        assertEquals(2, productos.size(), "Debería haber 2 productos en categoria1");
        
        for (Producto p : productos) {
            assertEquals("categoria1", p.getCategoria());
        }
    }
    
    @Test
    @Order(8)
    @DisplayName("Buscar por rango de precio")
    void testBuscarPorRangoPrecio() {
        List<Producto> productos = dao.buscarPorRangoPrecio(15.0, 25.0);
        
        assertNotNull(productos);
        for (Producto p : productos) {
            assertTrue(p.getPrecio() >= 15.0 && p.getPrecio() <= 25.0,
                    "Precio fuera de rango: " + p.getPrecio());
        }
    }
    
    @Test
    @Order(9)
    @DisplayName("Buscar productos con stock")
    void testBuscarConStock() {
        List<Producto> productos = dao.buscarConStock();
        
        assertNotNull(productos);
        for (Producto p : productos) {
            assertTrue(p.getStock() > 0, "Producto sin stock: " + p.getNombre());
        }
    }
    
    @Test
    @Order(10)
    @DisplayName("Buscar productos sin stock")
    void testBuscarSinStock() {
        List<Producto> productos = dao.buscarSinStock();
        
        assertNotNull(productos);
        assertFalse(productos.isEmpty(), "Debería haber al menos 1 producto sin stock");
        
        for (Producto p : productos) {
            assertEquals(0, p.getStock(), "Stock debería ser 0");
        }
    }
    
    @Test
    @Order(11)
    @DisplayName("Buscar por nombre parcial")
    void testBuscarPorNombre() {
        List<Producto> productos = dao.buscarPorNombre("Producto");
        
        assertNotNull(productos);
        assertTrue(productos.size() >= 3, "Debería encontrar productos con 'Producto'");
    }
    
    @Test
    @Order(12)
    @DisplayName("Obtener productos más caros")
    void testObtenerMasCaros() {
        List<Producto> productos = dao.obtenerMasCaros(2);
        
        assertNotNull(productos);
        assertEquals(2, productos.size(), "Debería devolver exactamente 2");
        
        // Verificar orden descendente
        assertTrue(productos.get(0).getPrecio() >= productos.get(1).getPrecio(),
                "Deberían estar ordenados por precio descendente");
    }
    
    // ═══════════════════════════════════════════════════════════════════════════
    // TESTS DE UPDATE
    // ═══════════════════════════════════════════════════════════════════════════
    
    @Test
    @Order(13)
    @DisplayName("Actualizar producto")
    void testActualizarProducto() {
        Producto p = dao.buscarPorId(idProductoTest);
        assertNotNull(p);
        
        String nuevoNombre = "Test Laptop MODIFICADO";
        double nuevoPrecio = 1099.99;
        
        p.setNombre(nuevoNombre);
        p.setPrecio(nuevoPrecio);
        
        dao.actualizar(p);
        
        // Verificar cambios
        Producto actualizado = dao.buscarPorId(idProductoTest);
        assertEquals(nuevoNombre, actualizado.getNombre());
        assertEquals(nuevoPrecio, actualizado.getPrecio(), 0.01);
    }
    
    @Test
    @Order(14)
    @DisplayName("Actualizar stock")
    void testActualizarStock() {
        int nuevoStock = 99;
        
        dao.actualizarStock(idProductoTest, nuevoStock);
        
        Producto p = dao.buscarPorId(idProductoTest);
        assertEquals(nuevoStock, p.getStock());
    }
    
    // ═══════════════════════════════════════════════════════════════════════════
    // TESTS DE DELETE
    // ═══════════════════════════════════════════════════════════════════════════
    
    @Test
    @Order(15)
    @DisplayName("Eliminar producto")
    void testEliminarProducto() {
        // Insertar uno para eliminar
        Producto p = new Producto("Para Eliminar", 1.0, 1, "eliminar");
        dao.insertar(p);
        String id = p.getId();
        
        // Verificar que existe
        assertNotNull(dao.buscarPorId(id));
        
        // Eliminar
        dao.eliminar(id);
        
        // Verificar que ya no existe
        assertNull(dao.buscarPorId(id), "El producto debería haberse eliminado");
    }
    
    @Test
    @Order(16)
    @DisplayName("Contar productos")
    void testContarProductos() {
        long count = dao.contar();
        assertTrue(count > 0, "Debería haber productos");
    }
}
