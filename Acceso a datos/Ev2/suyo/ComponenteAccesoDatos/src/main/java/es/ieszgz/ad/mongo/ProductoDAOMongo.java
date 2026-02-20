package es.ieszgz.ad.mongo;

import es.ieszgz.ad.componente.ProductoDAO;
import es.ieszgz.ad.modelo.Producto;
import es.ieszgz.ad.util.ConfigLoader;

import com.mongodb.client.*;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import static com.mongodb.client.model.Filters.*;

/**
 * Implementación del componente de acceso a datos para MongoDB.
 * 
 * <p>Utiliza el driver oficial de MongoDB para Java
 * y gestiona documentos en una colección de productos.</p>
 * 
 * <h2>Configuración requerida en config.properties:</h2>
 * <pre>
 * mongo.uri=mongodb://localhost:27017
 * mongo.database=tienda
 * mongo.collection=productos
 * </pre>
 * 
 * <h2>Estructura del documento:</h2>
 * <pre>
 * {
 *     "_id": ObjectId("..."),
 *     "nombre": "Laptop HP",
 *     "precio": 899.99,
 *     "stock": 15,
 *     "categoria": "informatica",
 *     "fechaAlta": ISODate("...")
 * }
 * </pre>
 * 
 * @author CPIFP Los Enlaces
 * @version 1.0
 * @since 2024
 */
public class ProductoDAOMongo implements ProductoDAO {
    
    /** Cliente de MongoDB */
    private MongoClient cliente;
    
    /** Base de datos */
    private MongoDatabase database;
    
    /** Colección de productos */
    private MongoCollection<Document> coleccion;
    
    // ═══════════════════════════════════════════════════════════════════
    // CONEXIÓN
    // ═══════════════════════════════════════════════════════════════════
    
    @Override
    public void conectar() {
        String uri = ConfigLoader.get("mongo.uri");
        String dbName = ConfigLoader.get("mongo.database");
        String collName = ConfigLoader.get("mongo.collection");
        
        cliente = MongoClients.create(uri);
        database = cliente.getDatabase(dbName);
        coleccion = database.getCollection(collName);
        
        System.out.println("✅ Conectado a MongoDB: " + dbName + "." + collName);
    }
    
    @Override
    public void desconectar() {
        if (cliente != null) {
            cliente.close();
            System.out.println("🔌 Desconectado de MongoDB");
        }
    }
    
    @Override
    public boolean estaConectado() {
        if (cliente == null) return false;
        try {
            // Intenta una operación simple para verificar conexión
            database.listCollectionNames().first();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    // ═══════════════════════════════════════════════════════════════════
    // CRUD - CREATE
    // ═══════════════════════════════════════════════════════════════════
    
    @Override
    public void insertar(Producto p) {
        Document doc = productoToDocument(p);
        coleccion.insertOne(doc);
        
        // Asignar el ID generado al objeto
        p.setId(doc.getObjectId("_id").toHexString());
        
        System.out.println("➕ Insertado: " + p.getNombre());
    }
    
    // ═══════════════════════════════════════════════════════════════════
    // CRUD - READ
    // ═══════════════════════════════════════════════════════════════════
    
    @Override
    public Producto buscarPorId(String id) {
        try {
            Document doc = coleccion.find(eq("_id", new ObjectId(id))).first();
            return doc != null ? documentToProducto(doc) : null;
        } catch (IllegalArgumentException e) {
            // ID no válido como ObjectId
            return null;
        }
    }
    
    @Override
    public List<Producto> buscarTodos() {
        List<Producto> productos = new ArrayList<>();
        
        for (Document doc : coleccion.find()) {
            productos.add(documentToProducto(doc));
        }
        
        return productos;
    }
    
    @Override
    public long contar() {
        return coleccion.countDocuments();
    }
    
    // ═══════════════════════════════════════════════════════════════════
    // CRUD - UPDATE
    // ═══════════════════════════════════════════════════════════════════
    
    @Override
    public void actualizar(Producto p) {
        Document updateDoc = new Document("$set", new Document()
                .append("nombre", p.getNombre())
                .append("precio", p.getPrecio())
                .append("stock", p.getStock())
                .append("categoria", p.getCategoria()));
        
        coleccion.updateOne(eq("_id", new ObjectId(p.getId())), updateDoc);
        System.out.println("✏️ Actualizado: " + p.getNombre());
    }
    
    @Override
    public void actualizarStock(String id, int nuevoStock) {
        Document updateDoc = new Document("$set", new Document("stock", nuevoStock));
        coleccion.updateOne(eq("_id", new ObjectId(id)), updateDoc);
    }
    
    // ═══════════════════════════════════════════════════════════════════
    // CRUD - DELETE
    // ═══════════════════════════════════════════════════════════════════
    
    @Override
    public void eliminar(String id) {
        try {
            DeleteResult result = coleccion.deleteOne(eq("_id", new ObjectId(id)));
            if (result.getDeletedCount() > 0) {
                System.out.println("🗑️ Eliminado producto ID: " + id);
            }
        } catch (IllegalArgumentException e) {
            System.err.println("⚠️ ID no válido: " + id);
        }
    }
    
    @Override
    public long eliminarTodos() {
        DeleteResult result = coleccion.deleteMany(new Document());
        return result.getDeletedCount();
    }
    
    // ═══════════════════════════════════════════════════════════════════
    // CONSULTAS ESPECÍFICAS
    // ═══════════════════════════════════════════════════════════════════
    
    @Override
    public List<Producto> buscarPorCategoria(String categoria) {
        List<Producto> productos = new ArrayList<>();
        
        for (Document doc : coleccion.find(eq("categoria", categoria))) {
            productos.add(documentToProducto(doc));
        }
        
        return productos;
    }
    
    @Override
    public List<Producto> buscarPorRangoPrecio(double precioMin, double precioMax) {
        List<Producto> productos = new ArrayList<>();
        
        // Usando operadores: $gte (>=) y $lte (<=)
        for (Document doc : coleccion.find(and(
                gte("precio", precioMin),
                lte("precio", precioMax)
        )).sort(Sorts.ascending("precio"))) {
            productos.add(documentToProducto(doc));
        }
        
        return productos;
    }
    
    @Override
    public List<Producto> buscarConStock() {
        List<Producto> productos = new ArrayList<>();
        
        for (Document doc : coleccion.find(gt("stock", 0))) {
            productos.add(documentToProducto(doc));
        }
        
        return productos;
    }
    
    @Override
    public List<Producto> buscarSinStock() {
        List<Producto> productos = new ArrayList<>();
        
        for (Document doc : coleccion.find(eq("stock", 0))) {
            productos.add(documentToProducto(doc));
        }
        
        return productos;
    }
    
    @Override
    public List<Producto> buscarPorNombre(String texto) {
        List<Producto> productos = new ArrayList<>();
        
        // Búsqueda con regex (case insensitive)
        Pattern pattern = Pattern.compile(texto, Pattern.CASE_INSENSITIVE);
        
        for (Document doc : coleccion.find(regex("nombre", pattern))) {
            productos.add(documentToProducto(doc));
        }
        
        return productos;
    }
    
    @Override
    public List<Producto> obtenerMasCaros(int limite) {
        List<Producto> productos = new ArrayList<>();
        
        for (Document doc : coleccion.find()
                .sort(Sorts.descending("precio"))
                .limit(limite)) {
            productos.add(documentToProducto(doc));
        }
        
        return productos;
    }
    
    // ═══════════════════════════════════════════════════════════════════
    // MÉTODOS DE CONVERSIÓN
    // ═══════════════════════════════════════════════════════════════════
    
    /**
     * Convierte un objeto Producto a Document de MongoDB.
     * 
     * @param p producto a convertir
     * @return documento BSON
     */
    private Document productoToDocument(Producto p) {
        Document doc = new Document()
                .append("nombre", p.getNombre())
                .append("precio", p.getPrecio())
                .append("stock", p.getStock())
                .append("categoria", p.getCategoria());
        
        if (p.getFechaAlta() != null) {
            doc.append("fechaAlta", Date.from(
                    p.getFechaAlta().atZone(ZoneId.systemDefault()).toInstant()));
        }
        
        return doc;
    }
    
    /**
     * Convierte un Document de MongoDB a objeto Producto.
     * 
     * @param doc documento BSON
     * @return objeto Producto
     */
    private Producto documentToProducto(Document doc) {
        Producto p = new Producto();
        
        p.setId(doc.getObjectId("_id").toHexString());
        p.setNombre(doc.getString("nombre"));
        p.setPrecio(doc.getDouble("precio"));
        p.setStock(doc.getInteger("stock"));
        p.setCategoria(doc.getString("categoria"));
        
        Date fecha = doc.getDate("fechaAlta");
        if (fecha != null) {
            p.setFechaAlta(LocalDateTime.ofInstant(
                    fecha.toInstant(), ZoneId.systemDefault()));
        }
        
        return p;
    }
}
