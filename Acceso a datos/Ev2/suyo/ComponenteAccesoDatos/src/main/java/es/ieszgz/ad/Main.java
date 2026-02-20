package es.ieszgz.ad;

import es.ieszgz.ad.componente.ProductoDAO;
import es.ieszgz.ad.factory.DAOFactory;
import es.ieszgz.ad.modelo.Producto;
import es.ieszgz.ad.util.ConfigLoader;

import java.util.List;

/**
 * Clase principal de demostración del componente de acceso a datos.
 * 
 * <p>Muestra el uso del patrón DAO y Factory para trabajar
 * con diferentes bases de datos de forma transparente.</p>
 * 
 * @author CPIFP Los Enlaces
 * @version 1.0
 */
public class Main {
    
    public static void main(String[] args) {
        
        imprimirCabecera();
        
        // Mostrar configuración cargada
        ConfigLoader.debug();
        System.out.println();
        
        // Crear el DAO usando la factoría (lee db.active de config.properties)
        ProductoDAO dao = DAOFactory.crearProductoDAO();
        
        try {
            // ═══════════════════════════════════════════════════════════════
            // 1. CONECTAR
            // ═══════════════════════════════════════════════════════════════
            System.out.println("📡 Conectando a " + DAOFactory.getTipoActivo() + "...\n");
            dao.conectar();
            
            // ═══════════════════════════════════════════════════════════════
            // 2. INSERTAR DATOS DE PRUEBA
            // ═══════════════════════════════════════════════════════════════
            System.out.println("\n╔═══════════════════════════════════════════════════════════╗");
            System.out.println("║               INSERTANDO PRODUCTOS                        ║");
            System.out.println("╚═══════════════════════════════════════════════════════════╝");
            
            dao.insertar(new Producto("Laptop HP Pavilion", 899.99, 15, "informatica"));
            dao.insertar(new Producto("Monitor LG 27\"", 299.50, 30, "informatica"));
            dao.insertar(new Producto("Teclado Mecánico RGB", 79.99, 50, "perifericos"));
            dao.insertar(new Producto("Ratón Logitech MX", 89.00, 45, "perifericos"));
            dao.insertar(new Producto("Webcam HD 1080p", 59.99, 25, "perifericos"));
            dao.insertar(new Producto("SSD Samsung 1TB", 129.99, 0, "almacenamiento"));
            
            // ═══════════════════════════════════════════════════════════════
            // 3. LISTAR TODOS
            // ═══════════════════════════════════════════════════════════════
            System.out.println("\n╔═══════════════════════════════════════════════════════════╗");
            System.out.println("║               LISTADO DE PRODUCTOS                        ║");
            System.out.println("╚═══════════════════════════════════════════════════════════╝");
            
            List<Producto> todos = dao.buscarTodos();
            System.out.println("Total productos: " + todos.size());
            System.out.println("─".repeat(60));
            
            for (Producto p : todos) {
                System.out.printf("  • %-25s %8.2f€  (stock: %d)%n", 
                        p.getNombre(), p.getPrecio(), p.getStock());
            }
            
            // ═══════════════════════════════════════════════════════════════
            // 4. CONSULTAS ESPECÍFICAS
            // ═══════════════════════════════════════════════════════════════
            System.out.println("\n╔═══════════════════════════════════════════════════════════╗");
            System.out.println("║               CONSULTAS ESPECÍFICAS                       ║");
            System.out.println("╚═══════════════════════════════════════════════════════════╝");
            
            // Por categoría
            System.out.println("\n🔍 Productos de 'perifericos':");
            for (Producto p : dao.buscarPorCategoria("perifericos")) {
                System.out.println("   • " + p.getNombre());
            }
            
            // Rango de precio
            System.out.println("\n🔍 Productos entre 50€ y 100€:");
            for (Producto p : dao.buscarPorRangoPrecio(50, 100)) {
                System.out.printf("   • %-25s %8.2f€%n", p.getNombre(), p.getPrecio());
            }
            
            // Sin stock
            System.out.println("\n🔍 Productos agotados:");
            List<Producto> agotados = dao.buscarSinStock();
            if (agotados.isEmpty()) {
                System.out.println("   ✅ No hay productos agotados");
            } else {
                for (Producto p : agotados) {
                    System.out.println("   ⚠️ " + p.getNombre());
                }
            }
            
            // Top 3 más caros
            System.out.println("\n🔍 Top 3 productos más caros:");
            int pos = 1;
            for (Producto p : dao.obtenerMasCaros(3)) {
                System.out.printf("   %d. %-25s %8.2f€%n", pos++, p.getNombre(), p.getPrecio());
            }
            
            // Búsqueda por nombre
            System.out.println("\n🔍 Búsqueda 'logitech':");
            for (Producto p : dao.buscarPorNombre("logitech")) {
                System.out.println("   • " + p);
            }
            
            // ═══════════════════════════════════════════════════════════════
            // 5. ACTUALIZAR
            // ═══════════════════════════════════════════════════════════════
            System.out.println("\n╔═══════════════════════════════════════════════════════════╗");
            System.out.println("║               ACTUALIZACIÓN                               ║");
            System.out.println("╚═══════════════════════════════════════════════════════════╝");
            
            List<Producto> lista = dao.buscarPorNombre("SSD");
            if (!lista.isEmpty()) {
                Producto ssd = lista.get(0);
                System.out.println("Antes: " + ssd);
                
                // Actualizar stock
                dao.actualizarStock(ssd.getId(), 20);
                
                // Verificar
                Producto actualizado = dao.buscarPorId(ssd.getId());
                System.out.println("Después: " + actualizado);
            }
            
            // ═══════════════════════════════════════════════════════════════
            // 6. ESTADÍSTICAS FINALES
            // ═══════════════════════════════════════════════════════════════
            System.out.println("\n╔═══════════════════════════════════════════════════════════╗");
            System.out.println("║               ESTADÍSTICAS                                ║");
            System.out.println("╚═══════════════════════════════════════════════════════════╝");
            
            long total = dao.contar();
            long conStock = dao.buscarConStock().size();
            long sinStock = dao.buscarSinStock().size();
            
            System.out.println("  📊 Total productos: " + total);
            System.out.println("  ✅ Con stock: " + conStock);
            System.out.println("  ⚠️ Sin stock: " + sinStock);
            
        } catch (Exception e) {
            System.err.println("\n❌ ERROR: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // ═══════════════════════════════════════════════════════════════
            // 7. DESCONECTAR (siempre)
            // ═══════════════════════════════════════════════════════════════
            System.out.println();
            dao.desconectar();
        }
        
        imprimirPie();
    }
    
    private static void imprimirCabecera() {
        System.out.println();
        System.out.println("╔═══════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                                   ║");
        System.out.println("║   COMPONENTE DE ACCESO A DATOS - DEMOSTRACIÓN                     ║");
        System.out.println("║   ───────────────────────────────────────────                     ║");
        System.out.println("║   Módulo: Acceso a Datos | UD6: Componentes                       ║");
        System.out.println("║   CPIFP Los Enlaces                                               ║");
        System.out.println("║                                                                   ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════════╝");
        System.out.println();
    }
    
    private static void imprimirPie() {
        System.out.println();
        System.out.println("╔═══════════════════════════════════════════════════════════════════╗");
        System.out.println("║   ✅ DEMOSTRACIÓN COMPLETADA                                      ║");
        System.out.println("║                                                                   ║");
        System.out.println("║   💡 Cambia db.active en config.properties para probar con       ║");
        System.out.println("║      otra base de datos (mysql / mongo)                           ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════════╝");
    }
}
