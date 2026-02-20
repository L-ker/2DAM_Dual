-- ═══════════════════════════════════════════════════════════════════════════════
-- SCRIPT DE CREACIÓN DE BASE DE DATOS PARA EL COMPONENTE
-- Módulo: Acceso a Datos | UD6: Programación de Componentes
-- CPIFP Los Enlaces
-- ═══════════════════════════════════════════════════════════════════════════════

-- ─────────────────────────────────────────────────────────────────────────────────
-- 1. CREAR BASE DE DATOS
-- ─────────────────────────────────────────────────────────────────────────────────
CREATE DATABASE IF NOT EXISTS tienda
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_spanish_ci;

USE tienda;

-- ─────────────────────────────────────────────────────────────────────────────────
-- 2. CREAR TABLA PRODUCTOS
-- ─────────────────────────────────────────────────────────────────────────────────
DROP TABLE IF EXISTS productos;

CREATE TABLE productos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    precio DECIMAL(10, 2) NOT NULL,
    stock INT DEFAULT 0,
    categoria VARCHAR(50),
    fecha_alta TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    -- Índices para optimizar consultas
    INDEX idx_categoria (categoria),
    INDEX idx_precio (precio),
    INDEX idx_stock (stock)
) ENGINE=InnoDB;

-- ─────────────────────────────────────────────────────────────────────────────────
-- 3. INSERTAR DATOS DE PRUEBA (OPCIONAL)
-- Descomenta si quieres datos iniciales
-- ─────────────────────────────────────────────────────────────────────────────────

/*
INSERT INTO productos (nombre, precio, stock, categoria) VALUES
    ('Laptop HP Pavilion', 899.99, 15, 'informatica'),
    ('Monitor LG 27"', 299.50, 30, 'informatica'),
    ('Teclado Mecánico RGB', 79.99, 50, 'perifericos'),
    ('Ratón Logitech MX', 89.00, 45, 'perifericos'),
    ('Webcam HD 1080p', 59.99, 25, 'perifericos'),
    ('SSD Samsung 1TB', 129.99, 0, 'almacenamiento'),
    ('Disco Duro 2TB', 79.99, 20, 'almacenamiento'),
    ('Memoria RAM 16GB', 69.99, 35, 'componentes'),
    ('Tarjeta Gráfica RTX', 599.99, 5, 'componentes'),
    ('Fuente 750W', 89.99, 18, 'componentes');
*/

-- ─────────────────────────────────────────────────────────────────────────────────
-- 4. VERIFICAR CREACIÓN
-- ─────────────────────────────────────────────────────────────────────────────────
DESCRIBE productos;
SELECT 'Base de datos creada correctamente' AS resultado;
