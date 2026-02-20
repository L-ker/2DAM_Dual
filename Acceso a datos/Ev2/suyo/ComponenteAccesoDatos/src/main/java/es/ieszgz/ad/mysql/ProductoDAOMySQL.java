package es.ieszgz.ad.mysql;

import es.ieszgz.ad.componente.ProductoDAO;
import es.ieszgz.ad.modelo.Producto;
import es.ieszgz.ad.util.ConfigLoader;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación del componente de acceso a datos para MySQL.
 * 
 * <p>Utiliza JDBC para conectar con una base de datos MySQL
 * y gestionar la persistencia de productos.</p>
 * 
 * <h2>Configuración requerida en config.properties:</h2>
 * <pre>
 * mysql.url=jdbc:mysql://localhost:3306/tienda
 * mysql.user=root
 * mysql.password=secret
 * </pre>
 * 
 * <h2>Ejemplo de uso:</h2>
 * <pre>{@code
 * ProductoDAO dao = new ProductoDAOMySQL();
 * dao.conectar();
 * 
 * Producto p = new Producto("Laptop", 999.99, 10);
 * dao.insertar(p);
 * 
 * List<Producto> todos = dao.buscarTodos();
 * dao.desconectar();
 * }</pre>
 * 
 * @author CPIFP Los Enlaces
 * @version 1.0
 * @since 2024
 */
public class ProductoDAOMySQL implements ProductoDAO {
    
    /** Conexión a la base de datos */
    private Connection conexion;
    
    // ═══════════════════════════════════════════════════════════════════
    // CONEXIÓN
    // ═══════════════════════════════════════════════════════════════════
    
    @Override
    public void conectar() throws SQLException {
        String url = ConfigLoader.get("mysql.url");
        String user = ConfigLoader.get("mysql.user");
        String password = ConfigLoader.get("mysql.password");
        
        conexion = DriverManager.getConnection(url, user, password);
        System.out.println("✅ Conectado a MySQL: " + url);
    }
    
    @Override
    public void desconectar() {
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("🔌 Desconectado de MySQL");
            } catch (SQLException e) {
                System.err.println("⚠️ Error al desconectar: " + e.getMessage());
            }
        }
    }
    
    @Override
    public boolean estaConectado() {
        try {
            return conexion != null && !conexion.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }
    
    // ═══════════════════════════════════════════════════════════════════
    // CRUD - CREATE
    // ═══════════════════════════════════════════════════════════════════
    
    @Override
    public void insertar(Producto p) {
        String sql = "INSERT INTO productos (nombre, precio, stock, categoria) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, p.getNombre());
            pstmt.setDouble(2, p.getPrecio());
            pstmt.setInt(3, p.getStock());
            pstmt.setString(4, p.getCategoria());
            
            pstmt.executeUpdate();
            
            // Obtener el ID generado
            try (ResultSet keys = pstmt.getGeneratedKeys()) {
                if (keys.next()) {
                    p.setId(keys.getInt(1));
                }
            }
            
            System.out.println("➕ Insertado: " + p.getNombre());
            
        } catch (SQLException e) {
            System.err.println("❌ Error al insertar: " + e.getMessage());
        }
    }
    
    // ═══════════════════════════════════════════════════════════════════
    // CRUD - READ
    // ═══════════════════════════════════════════════════════════════════
    
    @Override
    public Producto buscarPorId(String id) {
        String sql = "SELECT * FROM productos WHERE id = ?";
        
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, Integer.parseInt(id));
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapearProducto(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al buscar: " + e.getMessage());
        }
        return null;
    }
    
    @Override
    public List<Producto> buscarTodos() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos ORDER BY id";
        
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                productos.add(mapearProducto(rs));
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al listar: " + e.getMessage());
        }
        return productos;
    }
    
    @Override
    public long contar() {
        String sql = "SELECT COUNT(*) FROM productos";
        
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getLong(1);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al contar: " + e.getMessage());
        }
        return 0;
    }
    
    // ═══════════════════════════════════════════════════════════════════
    // CRUD - UPDATE
    // ═══════════════════════════════════════════════════════════════════
    
    @Override
    public void actualizar(Producto p) {
        String sql = "UPDATE productos SET nombre = ?, precio = ?, stock = ?, categoria = ? WHERE id = ?";
        
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, p.getNombre());
            pstmt.setDouble(2, p.getPrecio());
            pstmt.setInt(3, p.getStock());
            pstmt.setString(4, p.getCategoria());
            pstmt.setInt(5, Integer.parseInt(p.getId()));
            
            int filas = pstmt.executeUpdate();
            if (filas > 0) {
                System.out.println("✏️ Actualizado: " + p.getNombre());
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar: " + e.getMessage());
        }
    }
    
    @Override
    public void actualizarStock(String id, int nuevoStock) {
        String sql = "UPDATE productos SET stock = ? WHERE id = ?";
        
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, nuevoStock);
            pstmt.setInt(2, Integer.parseInt(id));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar stock: " + e.getMessage());
        }
    }
    
    // ═══════════════════════════════════════════════════════════════════
    // CRUD - DELETE
    // ═══════════════════════════════════════════════════════════════════
    
    @Override
    public void eliminar(String id) {
        String sql = "DELETE FROM productos WHERE id = ?";
        
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, Integer.parseInt(id));
            int filas = pstmt.executeUpdate();
            if (filas > 0) {
                System.out.println("🗑️ Eliminado producto ID: " + id);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar: " + e.getMessage());
        }
    }
    
    @Override
    public long eliminarTodos() {
        String sql = "DELETE FROM productos";
        
        try (Statement stmt = conexion.createStatement()) {
            return stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar todos: " + e.getMessage());
            return 0;
        }
    }
    
    // ═══════════════════════════════════════════════════════════════════
    // CONSULTAS ESPECÍFICAS
    // ═══════════════════════════════════════════════════════════════════
    
    @Override
    public List<Producto> buscarPorCategoria(String categoria) {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos WHERE categoria = ?";
        
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, categoria);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    productos.add(mapearProducto(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
        return productos;
    }
    
    @Override
    public List<Producto> buscarPorRangoPrecio(double precioMin, double precioMax) {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos WHERE precio BETWEEN ? AND ? ORDER BY precio";
        
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setDouble(1, precioMin);
            pstmt.setDouble(2, precioMax);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    productos.add(mapearProducto(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
        return productos;
    }
    
    @Override
    public List<Producto> buscarConStock() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos WHERE stock > 0";
        
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                productos.add(mapearProducto(rs));
            }
        } catch (SQLException e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
        return productos;
    }
    
    @Override
    public List<Producto> buscarSinStock() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos WHERE stock = 0";
        
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                productos.add(mapearProducto(rs));
            }
        } catch (SQLException e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
        return productos;
    }
    
    @Override
    public List<Producto> buscarPorNombre(String texto) {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos WHERE LOWER(nombre) LIKE ?";
        
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, "%" + texto.toLowerCase() + "%");
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    productos.add(mapearProducto(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
        return productos;
    }
    
    @Override
    public List<Producto> obtenerMasCaros(int limite) {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos ORDER BY precio DESC LIMIT ?";
        
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, limite);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    productos.add(mapearProducto(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
        return productos;
    }
    
    // ═══════════════════════════════════════════════════════════════════
    // MÉTODOS AUXILIARES
    // ═══════════════════════════════════════════════════════════════════
    
    /**
     * Convierte un ResultSet en un objeto Producto.
     * 
     * @param rs ResultSet posicionado en una fila
     * @return objeto Producto con los datos de la fila
     * @throws SQLException si hay error al leer
     */
    private Producto mapearProducto(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        p.setId(rs.getInt("id"));
        p.setNombre(rs.getString("nombre"));
        p.setPrecio(rs.getDouble("precio"));
        p.setStock(rs.getInt("stock"));
        p.setCategoria(rs.getString("categoria"));
        
        Timestamp ts = rs.getTimestamp("fecha_alta");
        if (ts != null) {
            p.setFechaAlta(ts.toLocalDateTime());
        }
        
        return p;
    }
}
