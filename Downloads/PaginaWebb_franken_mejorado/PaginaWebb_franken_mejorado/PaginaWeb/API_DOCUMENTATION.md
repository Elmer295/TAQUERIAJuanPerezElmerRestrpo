# 📚 Documentación Completa de API - Taquería Franken

## 🎯 Introducción

Esta documentación proporciona una guía completa de todos los endpoints disponibles en la API de **Taquería Franken**. La API está documentada usando **Swagger/OpenAPI 3.0** con **SpringDoc-OpenAPI**, proporcionando una interfaz interactiva para explorar y probar todos los endpoints.

---

## 🚀 Acceso a la Documentación Interactiva

### Swagger UI (Recomendado)
```
URL: http://localhost:8081/swagger-ui.html
```
Interfaz web interactiva donde puedes visualizar, entender y probar todos los endpoints.

### OpenAPI JSON
```
URL: http://localhost:8081/api-docs
```
Especificación completa en formato JSON compatible con herramientas como Postman.

---

## 📋 Tabla de Contenidos

1. [Controllers](#controllers)
2. [Endpoints por Categoría](#endpoints-por-categoría)
3. [Modelos de Datos](#modelos-de-datos)
4. [Códigos de Respuesta HTTP](#códigos-de-respuesta-http)
5. [Ejemplos de Uso](#ejemplos-de-uso)
6. [Instalación y Configuración](#instalación-y-configuración)

---

## 🎮 Controllers

### 1. HomeController
**Descripción:** Gestiona las páginas principales de la aplicación.

**Base Path:** `/`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/` | Redirige al login |
| GET | `/index` | Página de inicio |
| GET | `/mision` | Página de misión |
| GET | `/vision` | Página de visión |
| GET | `/objetivo` | Página de objetivos |
| GET | `/ubicacion` | Ubicación de la taquería |
| GET | `/contacto` | Página de contacto |

---

### 2. UsuarioController
**Descripción:** Gestiona autenticación y gestión de usuarios.

**Base Path:** `/`

#### Registro

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/registro` | Mostrar formulario de registro |
| POST | `/registro` | Procesar nuevo registro de usuario |

**Request Body (POST /registro):**
```json
{
  "nombreUsuario": "usuario123",
  "email": "usuario@example.com",
  "contraseña": "password123",
  "confirmarContraseña": "password123"
}
```

**Response Success (201 Created):**
```json
{
  "id": 1,
  "nombreUsuario": "usuario123",
  "email": "usuario@example.com",
  "mensaje": "Usuario registrado exitosamente"
}
```

#### Login

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/login` | Mostrar formulario de login |
| POST | `/login` | Procesar login de usuario |

**Request Body (POST /login):**
```json
{
  "nombreUsuario": "usuario123",
  "contraseña": "password123"
}
```

**Response Success (200 OK):**
```json
{
  "id": 1,
  "nombreUsuario": "usuario123",
  "email": "usuario@example.com",
  "mensaje": "Login exitoso"
}
```

#### Logout

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/logout` | Cerrar sesión actual |

**Response (200 OK):**
```json
{
  "mensaje": "Sesión cerrada exitosamente"
}
```

---

### 3. OpcionController
**Descripción:** Gestiona el menú, carrito de compras y procesos de compra.

**Base Path:** `/menu`

#### Menú

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/menu` | Obtener lista completa de tacos disponibles |

**Response (200 OK):**
```json
{
  "tacos": [
    {
      "id": 1,
      "nombre": "Taco al Pastor",
      "descripcion": "Carne marinada con especias",
      "precio": 15000,
      "disponible": true
    }
  ],
  "total": 5
}
```

#### Carrito

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/menu/carrito/agregar` | Agregar taco al carrito |
| GET | `/menu/carrito` | Ver contenido del carrito |
| POST | `/menu/carrito/editar` | Actualizar cantidad de items |
| GET | `/menu/carrito/eliminar/{id}` | Eliminar item específico |
| GET | `/menu/carrito/cancelar` | Cancelar/limpiar carrito |

**Request Body (POST /carrito/agregar):**
```json
{
  "tacoId": 1,
  "cantidad": 2
}
```

**Response (200 OK):**
```json
{
  "id": 1,
  "usuarioId": 1,
  "items": [
    {
      "tacoId": 1,
      "cantidad": 2,
      "subtotal": 30000
    }
  ],
  "total": 30000,
  "mensaje": "Taco agregado al carrito"
}
```

**GET /carrito Response:**
```json
{
  "id": 1,
  "usuarioId": 1,
  "items": [
    {
      "tacoId": 1,
      "nombre": "Taco al Pastor",
      "cantidad": 2,
      "precioUnitario": 15000,
      "subtotal": 30000
    }
  ],
  "total": 30000,
  "cantidadItems": 1
}
```

#### Compra

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/menu/confirmarCompra` | Mostrar formulario de confirmación de compra |
| POST | `/menu/confirmarCompra` | Procesar compra y crear factura |

**Request Body (POST /confirmarCompra):**
```json
{
  "direccionEntrega": "Calle Principal 123",
  "telefonoContacto": "3001234567",
  "metodoPago": "TARJETA"
}
```

**Response Success (201 Created):**
```json
{
  "numeroFactura": "FAC-2025-0001",
  "fecha": "2025-12-06",
  "usuario": "usuario123",
  "items": [
    {
      "nombre": "Taco al Pastor",
      "cantidad": 2,
      "precioUnitario": 15000,
      "subtotal": 30000
    }
  ],
  "subtotal": 30000,
  "impuesto": 5700,
  "total": 35700,
  "estado": "CONFIRMADA",
  "mensaje": "Compra realizada exitosamente"
}
```

---

### 4. TacosController
**Descripción:** Gestiona la creación, edición y eliminación de tacos (Administrador).

**Base Path:** `/menu/tacos`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/menu/tacos/nuevo` | Mostrar formulario para crear taco |
| POST | `/menu/tacos/guardar` | Guardar nuevo taco |
| GET | `/menu/tacos/eliminar/{id}` | Mostrar confirmación de eliminación |
| POST | `/menu/tacos/eliminar/{id}` | Eliminar taco |

**Request Body (POST /tacos/guardar):**
```json
{
  "nombre": "Taco de Barbacoa",
  "descripcion": "Carne de res cocida lentamente",
  "precio": 16000,
  "ingredientes": "Carne, Cilantro, Cebolla",
  "picante": 2
}
```

**Response Success (201 Created):**
```json
{
  "id": 6,
  "nombre": "Taco de Barbacoa",
  "descripcion": "Carne de res cocida lentamente",
  "precio": 16000,
  "ingredientes": "Carne, Cilantro, Cebolla",
  "picante": 2,
  "disponible": true,
  "mensaje": "Taco creado exitosamente"
}
```

**DELETE (POST /tacos/eliminar/{id}):**
```json
{
  "id": 6,
  "mensaje": "Taco eliminado exitosamente"
}
```

---

## 📊 Endpoints por Categoría

### 🏠 Páginas Principales
```
GET  /              - Redirigir al login
GET  /index         - Página de inicio
GET  /mision        - Información de misión
GET  /vision        - Información de visión
GET  /objetivo      - Objetivos de la empresa
GET  /ubicacion     - Ubicación de la tienda
GET  /contacto      - Formulario de contacto
```

### 👤 Autenticación
```
GET  /login         - Mostrar formulario de login
POST /login         - Procesar login
GET  /registro      - Mostrar formulario de registro
POST /registro      - Procesar registro
GET  /logout        - Cerrar sesión
```

### 🌮 Menú y Carrito
```
GET  /menu                          - Ver menú de tacos
POST /menu/carrito/agregar          - Agregar taco al carrito
GET  /menu/carrito                  - Ver carrito
POST /menu/carrito/editar           - Editar cantidades en carrito
GET  /menu/carrito/eliminar/{id}    - Eliminar item del carrito
GET  /menu/carrito/cancelar         - Cancelar/Limpiar carrito
GET  /menu/confirmarCompra          - Ver formulario de compra
POST /menu/confirmarCompra          - Procesar compra
```

### 📝 Gestión de Tacos (Administrador)
```
GET  /menu/tacos/nuevo              - Formulario nuevo taco
POST /menu/tacos/guardar            - Guardar taco
GET  /menu/tacos/eliminar/{id}      - Confirmar eliminación
POST /menu/tacos/eliminar/{id}      - Eliminar taco
```

---

## 🗂️ Modelos de Datos

### Usuario
```json
{
  "id": 1,
  "nombreUsuario": "string",
  "email": "string",
  "contraseña": "string",
  "fechaRegistro": "2025-12-06",
  "activo": true
}
```

### Taco
```json
{
  "id": 1,
  "nombre": "string",
  "descripcion": "string",
  "precio": 15000,
  "ingredientes": "string",
  "picante": 1,
  "disponible": true,
  "fechaCreacion": "2025-12-06"
}
```

### Carrito
```json
{
  "id": 1,
  "usuarioId": 1,
  "items": [
    {
      "id": 1,
      "tacoId": 1,
      "cantidad": 2,
      "precioUnitario": 15000
    }
  ],
  "total": 30000,
  "fechaCreacion": "2025-12-06"
}
```

### Factura/Pedido
```json
{
  "id": 1,
  "numeroFactura": "FAC-2025-0001",
  "usuarioId": 1,
  "fecha": "2025-12-06",
  "items": [...],
  "subtotal": 30000,
  "impuesto": 5700,
  "total": 35700,
  "estado": "CONFIRMADA",
  "direccionEntrega": "string",
  "telefonoContacto": "string"
}
```

---

## 📡 Códigos de Respuesta HTTP

| Código | Significado | Descripción |
|--------|------------|-------------|
| **200** | OK | Solicitud exitosa |
| **201** | Created | Recurso creado exitosamente |
| **204** | No Content | Operación exitosa sin contenido |
| **400** | Bad Request | Solicitud inválida o datos faltantes |
| **401** | Unauthorized | Autenticación requerida |
| **403** | Forbidden | Acceso denegado |
| **404** | Not Found | Recurso no encontrado |
| **409** | Conflict | Conflicto (ej: usuario ya existe) |
| **500** | Internal Server Error | Error del servidor |
| **503** | Service Unavailable | Servicio no disponible |

---

## 💡 Ejemplos de Uso

### Ejemplo 1: Registro de Usuario
```bash
curl -X POST http://localhost:8081/registro \
  -H "Content-Type: application/json" \
  -d '{
    "nombreUsuario": "juanperez",
    "email": "juan@example.com",
    "contraseña": "MiPassword123",
    "confirmarContraseña": "MiPassword123"
  }'
```

**Respuesta:**
```json
{
  "id": 1,
  "nombreUsuario": "juanperez",
  "email": "juan@example.com",
  "mensaje": "Usuario registrado exitosamente"
}
```

---

### Ejemplo 2: Login
```bash
curl -X POST http://localhost:8081/login \
  -H "Content-Type: application/json" \
  -d '{
    "nombreUsuario": "juanperez",
    "contraseña": "MiPassword123"
  }'
```

**Respuesta:**
```json
{
  "id": 1,
  "nombreUsuario": "juanperez",
  "email": "juan@example.com",
  "mensaje": "Login exitoso"
}
```

---

### Ejemplo 3: Ver Menú
```bash
curl -X GET http://localhost:8081/menu \
  -H "Content-Type: application/json"
```

**Respuesta:**
```json
{
  "tacos": [
    {
      "id": 1,
      "nombre": "Taco al Pastor",
      "descripcion": "Carne marinada con especias",
      "precio": 15000,
      "disponible": true
    },
    {
      "id": 2,
      "nombre": "Taco de Barbacoa",
      "descripcion": "Carne de res cocida lentamente",
      "precio": 16000,
      "disponible": true
    }
  ],
  "total": 2
}
```

---

### Ejemplo 4: Agregar al Carrito
```bash
curl -X POST http://localhost:8081/menu/carrito/agregar \
  -H "Content-Type: application/json" \
  -d '{
    "tacoId": 1,
    "cantidad": 2
  }'
```

**Respuesta:**
```json
{
  "id": 1,
  "usuarioId": 1,
  "items": [
    {
      "tacoId": 1,
      "cantidad": 2,
      "subtotal": 30000
    }
  ],
  "total": 30000,
  "mensaje": "Taco agregado al carrito"
}
```

---

### Ejemplo 5: Procesar Compra
```bash
curl -X POST http://localhost:8081/menu/confirmarCompra \
  -H "Content-Type: application/json" \
  -d '{
    "direccionEntrega": "Calle Principal 123, Apartamento 5B",
    "telefonoContacto": "3001234567",
    "metodoPago": "TARJETA"
  }'
```

**Respuesta:**
```json
{
  "numeroFactura": "FAC-2025-0001",
  "fecha": "2025-12-06",
  "usuario": "juanperez",
  "items": [
    {
      "nombre": "Taco al Pastor",
      "cantidad": 2,
      "precioUnitario": 15000,
      "subtotal": 30000
    }
  ],
  "subtotal": 30000,
  "impuesto": 5700,
  "total": 35700,
  "estado": "CONFIRMADA",
  "mensaje": "Compra realizada exitosamente"
}
```

---

### Ejemplo 6: Crear Nuevo Taco (Admin)
```bash
curl -X POST http://localhost:8081/menu/tacos/guardar \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Taco de Camarón",
    "descripcion": "Camarón fresco con salsas caseras",
    "precio": 18000,
    "ingredientes": "Camarón, Cilantro, Cebolla, Limón",
    "picante": 3
  }'
```

**Respuesta:**
```json
{
  "id": 6,
  "nombre": "Taco de Camarón",
  "descripcion": "Camarón fresco con salsas caseras",
  "precio": 18000,
  "ingredientes": "Camarón, Cilantro, Cebolla, Limón",
  "picante": 3,
  "disponible": true,
  "mensaje": "Taco creado exitosamente"
}
```

---

## 🔧 Instalación y Configuración

### 1. Dependencia Maven
La siguiente dependencia ya está configurada en el `pom.xml`:

```xml
<!-- Swagger/SpringDoc OpenAPI -->
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.6.0</version>
</dependency>
```

### 2. Configuración en application.properties
```properties
# Swagger Configuration
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.swagger-ui.enabled=true
springdoc.api-docs.enabled=true
```

### 3. Clase de Configuración
Se incluye una clase `SwaggerConfig.java` con anotaciones `@OpenAPIDefinition` para personalizar la documentación.

### 4. Anotaciones en Controladores
Todos los controladores incluyen:
- `@Tag` - Categoría del controlador
- `@Operation` - Descripción de cada endpoint
- `@ApiResponse` - Respuestas posibles
- `@Parameter` - Documentación de parámetros
- `@RequestBody` - Documentación de body

---

## 📖 Cómo Usar la Documentación

### Opción 1: Swagger UI (Recomendado)
1. Inicia la aplicación: `mvn spring-boot:run`
2. Abre en tu navegador: `http://localhost:8081/swagger-ui.html`
3. Explora los endpoints en la interfaz interactiva
4. Prueba los endpoints directamente desde el navegador

### Opción 2: Postman
1. Importa la especificación OpenAPI
2. URL: `http://localhost:8081/api-docs`
3. Usa Postman para probar los endpoints

### Opción 3: cURL
Usa los ejemplos de curl proporcionados arriba

---

## ✅ Características de la Documentación

✨ **Interfaz Interactiva**
- Visualiza todos los endpoints
- Prueba directamente desde el navegador
- Ve ejemplos de request/response

🔍 **Búsqueda y Filtrado**
- Busca por nombre de endpoint
- Filtra por tags/categorías
- Organizado por controladores

📋 **Modelos de Datos**
- Esquemas JSON completos
- Tipos de datos y validaciones
- Ejemplos de valores

🧪 **Pruebas Interactivas**
- Probador de API integrado
- Visualiza headers, body, parámetros
- Respuestas en tiempo real

---

## 🆘 Solución de Problemas

### Swagger no carga
- Verifica que la aplicación esté corriendo en `http://localhost:8081`
- Asegúrate que la dependencia está en el `pom.xml`
- Reconstruye el proyecto: `mvn clean install`

### Endpoints no aparecen
- Verifica las anotaciones `@Operation` en los controladores
- Asegúrate que los controladores están en el paquete correcto
- Reconstruye y reinicia la aplicación

### Errores de CORS
- Configura CORS en la aplicación si accedes desde otro dominio
- Usa la URL local: `http://localhost:8081`

---

## 📞 Soporte

Para más información o reportar problemas:
- Contacta al equipo de desarrollo
- Email: desarrollo@taqueriafrankelen.com
- Documentación oficial SpringDoc: https://springdoc.org/

---

**Última actualización:** 6 de Diciembre, 2025  
**Versión:** 1.0  
**Estado:** Activo ✅
