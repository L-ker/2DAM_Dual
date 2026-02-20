# 📦 Componente de Acceso a Datos

**Módulo:** Acceso a Datos | **UD6:** Programación de Componentes  
**Ciclo:** DAM | **Centro:** CPIFP Los Enlaces

---

## 📋 Descripción

Componente Java reutilizable para acceso a bases de datos **MySQL** y **MongoDB**.  
Implementa el **patrón DAO** (Data Access Object) con una **Factory** para cambiar de BD sin modificar código.

## 🏗️ Estructura del Proyecto

```
ComponenteAccesoDatos/
├── pom.xml                              # Configuración Maven
├── sql/
│   └── crear_bd.sql                     # Script para crear BD MySQL
├── src/
│   ├── main/
│   │   ├── java/es/ieszgz/ad/
│   │   │   ├── Main.java                # Demo del componente
│   │   │   ├── componente/
│   │   │   │   ├── GenericDAO.java      # Interface genérica
│   │   │   │   └── ProductoDAO.java     # Interface específica
│   │   │   ├── modelo/
│   │   │   │   └── Producto.java        # Entidad
│   │   │   ├── mysql/
│   │   │   │   └── ProductoDAOMySQL.java
│   │   │   ├── mongo/
│   │   │   │   └── ProductoDAOMongo.java
│   │   │   ├── factory/
│   │   │   │   └── DAOFactory.java      # Patrón Factory
│   │   │   └── util/
│   │   │       └── ConfigLoader.java    # Carga configuración
│   │   └── resources/
│   │       └── config.properties        # ⚠️ CONFIGURAR
│   └── test/
│       └── java/es/ieszgz/ad/
│           └── ProductoDAOTest.java     # Tests JUnit 5
└── README.md
```

## 🚀 Configuración Rápida

### 1. Configurar Base de Datos

Edita `src/main/resources/config.properties`:

```properties
# Elige: mysql o mongo
db.active=mysql

# MySQL
mysql.url=jdbc:mysql://localhost:3306/tienda
mysql.user=root
mysql.password=TU_PASSWORD

# MongoDB
mongo.uri=mongodb://localhost:27017
mongo.database=tienda
mongo.collection=productos
```

### 2. Crear BD MySQL (si usas MySQL)

```bash
mysql -u root -p < sql/crear_bd.sql
```

### 3. Ejecutar

```bash
# Compilar
mvn clean compile

# Ejecutar demo
mvn exec:java

# Ejecutar tests
mvn test
```

## 💡 Uso del Componente

### Básico

```java
// 1. Crear DAO (lee config.properties)
ProductoDAO dao = DAOFactory.crearProductoDAO();

// 2. Conectar
dao.conectar();

// 3. Usar
dao.insertar(new Producto("Laptop", 999.99, 10, "informatica"));
List<Producto> todos = dao.buscarTodos();

// 4. Desconectar
dao.desconectar();
```

### Cambiar de BD en Runtime

```java
// Forzar MongoDB
ProductoDAO dao = DAOFactory.crearProductoDAO(TipoBD.MONGODB);

// O cambiar solo en config.properties:
// db.active=mongo
```

## 🔧 Patrones Implementados

| Patrón | Clase | Propósito |
|--------|-------|-----------|
| **DAO** | `GenericDAO`, `ProductoDAO` | Abstracción del acceso a datos |
| **Factory** | `DAOFactory` | Creación desacoplada de DAOs |
| **Singleton** | `ConfigLoader` | Carga única de configuración |

## 📊 Criterios de Evaluación Cubiertos

- ✅ **RA-a)** Ventajas/inconvenientes de componentes
- ✅ **RA-b)** Herramientas: Maven, JDBC, MongoDB Driver, JUnit
- ✅ **RA-d)** Conectores BD relacionales (MySQL)
- ✅ **RA-e)** Mapeo objeto-relacional (Producto ↔ tabla/documento)
- ✅ **RA-f)** BD orientadas a objetos (MongoDB)
- ✅ **RA-h)** Tests y documentación Javadoc
- ✅ **RA-i)** Integración en aplicaciones (Main)

## 📚 Dependencias

| Librería | Versión | Uso |
|----------|---------|-----|
| mysql-connector-j | 8.2.0 | Driver MySQL |
| mongodb-driver-sync | 4.11.1 | Driver MongoDB |
| junit-jupiter | 5.10.1 | Testing |
| slf4j + logback | 2.0.9 | Logging |

## 🧪 Ejecutar Tests

```bash
# Todos los tests
mvn test

# Test específico
mvn test -Dtest=ProductoDAOTest

# Con reporte
mvn surefire-report:report
```

## 📝 Generar Javadoc

```bash
mvn javadoc:javadoc
# Ver en: target/site/apidocs/index.html
```

## ⚠️ Solución de Problemas

### "Communications link failure"
- Verifica que MySQL esté corriendo
- Comprueba URL, usuario y password en config.properties

### "MongoTimeoutException"
- Verifica que MongoDB esté corriendo
- Si usas Atlas, comprueba la URI y que tu IP esté en whitelist

### "No se encuentra config.properties"
- Debe estar en `src/main/resources/`
- Ejecuta `mvn clean compile` para copiar recursos

---

**CPIFP Los Enlaces** | Acceso a Datos | DAM
