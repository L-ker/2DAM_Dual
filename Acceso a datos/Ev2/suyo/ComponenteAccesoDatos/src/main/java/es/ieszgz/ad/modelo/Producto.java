package es.ieszgz.ad.modelo;

import java.time.LocalDateTime;

/**
 * Entidad que representa un producto en el sistema.
 * 
 * <p>Esta clase es un POJO (Plain Old Java Object) que encapsula
 * los datos de un producto, independiente de la base de datos.</p>
 * 
 * @author CPIFP Los Enlaces
 * @version 1.0
 * @since 2024
 */
public class Producto {
    
    /** Identificador único (puede ser Integer para MySQL o String para MongoDB) */
    private String id;
    
    /** Nombre del producto */
    private String nombre;
    
    /** Precio en euros */
    private double precio;
    
    /** Cantidad en stock */
    private int stock;
    
    /** Categoría del producto */
    private String categoria;
    
    /** Fecha de alta en el sistema */
    private LocalDateTime fechaAlta;
    
    // ═══════════════════════════════════════════════════════════════════
    // CONSTRUCTORES
    // ═══════════════════════════════════════════════════════════════════
    
    /**
     * Constructor por defecto.
     */
    public Producto() {
        this.fechaAlta = LocalDateTime.now();
    }
    
    /**
     * Constructor con datos básicos.
     * 
     * @param nombre nombre del producto
     * @param precio precio en euros
     * @param stock cantidad disponible
     */
    public Producto(String nombre, double precio, int stock) {
        this();
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }
    
    /**
     * Constructor completo.
     * 
     * @param nombre nombre del producto
     * @param precio precio en euros
     * @param stock cantidad disponible
     * @param categoria categoría del producto
     */
    public Producto(String nombre, double precio, int stock, String categoria) {
        this(nombre, precio, stock);
        this.categoria = categoria;
    }
    
    // ═══════════════════════════════════════════════════════════════════
    // GETTERS Y SETTERS
    // ═══════════════════════════════════════════════════════════════════
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * Establece el ID desde un entero (útil para MySQL).
     * @param id identificador numérico
     */
    public void setId(int id) {
        this.id = String.valueOf(id);
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public double getPrecio() {
        return precio;
    }
    
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    public int getStock() {
        return stock;
    }
    
    public void setStock(int stock) {
        this.stock = stock;
    }
    
    public String getCategoria() {
        return categoria;
    }
    
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    public LocalDateTime getFechaAlta() {
        return fechaAlta;
    }
    
    public void setFechaAlta(LocalDateTime fechaAlta) {
        this.fechaAlta = fechaAlta;
    }
    
    // ═══════════════════════════════════════════════════════════════════
    // MÉTODOS DE UTILIDAD
    // ═══════════════════════════════════════════════════════════════════
    
    /**
     * Verifica si hay stock disponible.
     * @return true si stock > 0
     */
    public boolean hayStock() {
        return stock > 0;
    }
    
    /**
     * Calcula el valor total del stock.
     * @return precio × stock
     */
    public double valorStock() {
        return precio * stock;
    }
    
    @Override
    public String toString() {
        return String.format("Producto[id=%s, nombre='%s', precio=%.2f€, stock=%d, categoria='%s']",
                id, nombre, precio, stock, categoria);
    }
}
