# 🌮 Taquería Franken - Sistema de Venta Online

Aplicación web completa para la gestión y venta online de tacos. Incluye autenticación de usuarios, carrito de compras, procesamiento de pedidos y documentación completa de API.

## 📋 Tabla de Contenidos

- [Características](#características)
- [Requisitos](#requisitos)
- [Instalación](#instalación)
- [Uso](#uso)
- [Documentación](#documentación)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Tecnologías](#tecnologías)
- [Autores](#autores)

---

## ✨ Características

✅ **Autenticación de Usuarios**
- Registro de nuevos usuarios
- Login/Logout seguro
- Gestión de sesiones

🛒 **Carrito de Compras**
- Agregar/editar/eliminar productos
- Visualización de carrito
- Cálculo automático de totales

💰 **Procesamiento de Compras**
- Formulario de compra
- Generación de facturas
- Confirmación de pedidos

🍖 **Gestión de Menú**
- Visualización de tacos disponibles
- Creación de nuevos tacos (Admin)
- Eliminación de tacos

📱 **Interfaz Responsive**
- Diseño adaptable para móviles
- Navegación intuitiva
- Estilos personalizados

📚 **Documentación API**
- Swagger/OpenAPI integrado
- Interfaz interactiva para probar endpoints
- Documentación completa y detallada

---

## 🔧 Requisitos

- **Java:** JDK 17 o superior
- **Maven:** 3.8.1 o superior
- **Navegador:** Chrome, Firefox, Safari, Edge (versiones recientes)

### Verificar Requisitos

```bash
# Verificar Java
java -version

# Verificar Maven
mvn -version
```

---

## 📥 Instalación

### 1. Clonar el Repositorio

```bash
git clone https://github.com/Elmer295/TAQUERIAJuanPerezElmerRestrpo.git
cd PaginaWebb_franken_mejorado/PaginaWeb
```

### 2. Compilar el Proyecto

```bash
# Con Maven
mvn clean install

# O usando el wrapper incluido
./mvnw clean install  # En Linux/Mac
mvnw.cmd clean install  # En Windows
```

### 3. Ejecutar la Aplicación

```bash
# Con Maven
mvn spring-boot:run

# O usando el wrapper
./mvnw spring-boot:run  # Linux/Mac
mvnw.cmd spring-boot:run  # Windows
```

### 4. Acceder a la Aplicación

```
http://localhost:8081
```

---

## 🚀 Uso

### Flujo Principal

#### 1. Registro de Usuario
- Accede a `http://localhost:8081/registro`
- Completa el formulario con:
  - Nombre de usuario
  - Email
  - Contraseña (mínimo 8 caracteres)
  - Confirmación de contraseña

#### 2. Login
- Accede a `http://localhost:8081/login`
- Ingresa tus credenciales
- Se guardará tu sesión

#### 3. Explorar Menú
- Accede a `http://localhost:8081/menu`
- Visualiza todos los tacos disponibles
- Lee descripciones y precios

#### 4. Agregar al Carrito
- Selecciona la cantidad de tacos
- Haz clic en "Agregar al Carrito"
- Continúa comprando o ve al carrito

#### 5. Ver Carrito
- Accede a `http://localhost:8081/menu/carrito`
- Edita cantidades si es necesario
- Elimina items que no desees
- Procede al checkout

#### 6. Confirmar Compra
- Accede a `http://localhost:8081/menu/confirmarCompra`
- Ingresa dirección de entrega
- Ingresa teléfono de contacto
- Selecciona método de pago
- Confirma la compra

#### 7. Factura
- Recibirás número de factura
- Podrás consultar tu historial de compras

---

## 📚 Documentación

### 📖 Documentación Completa de API

La documentación completa de todos los endpoints está disponible en:

📄 **Archivo:** `API_DOCUMENTATION.md`

Incluye:
- ✅ Descripción de todos los controladores
- ✅ Listado completo de endpoints
- ✅ Ejemplos de request/response
- ✅ Códigos de estado HTTP
- ✅ Ejemplos de uso con cURL
- ✅ Modelos de datos

### 🔗 Swagger UI Interactivo

Una vez que la aplicación está corriendo, puedes acceder a:

```
http://localhost:8081/swagger-ui.html
```

**Características:**
- 🔍 Explora todos los endpoints
- 🧪 Prueba los endpoints directamente
- 📋 Visualiza esquemas de datos
- 📝 Ve ejemplos automáticos
- 🎯 Filtro por categorías

### 📋 Swagger JSON

Para herramientas como Postman, accede a:

```
http://localhost:8081/api-docs
```

---

## 📁 Estructura del Proyecto

```
PaginaWeb/
├── src/
│   ├── main/
│   │   ├── java/com/pagina/pagina4/
│   │   │   ├── Controller/
│   │   │   │   ├── HomeController.java
│   │   │   │   ├── UsuarioController.java
│   │   │   │   ├── OpcionController.java
│   │   │   │   └── TacosController.java
│   │   │   ├── Model/
│   │   │   ├── Service/
│   │   │   ├── Repository/
│   │   │   └── Pagina4Application.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── static/
│   │       │   ├── css/
│   │       │   │   ├── estilos.css
│   │       │   │   ├── navbar.css
│   │       │   │   ├── carrito.css
│   │       │   │   └── ...
│   │       │   └── img/
│   │       └── templates/
│   │           ├── index.html
│   │           ├── login.html
│   │           ├── registro.html
│   │           ├── menu.html
│   │           ├── carrito.html
│   │           ├── confirmarCompra.html
│   │           ├── Fragments/
│   │           │   ├── navbar.html
│   │           │   └── footer.html
│   │           └── ...
│   └── test/
├── pom.xml
├── SWAGGER_DOCUMENTATION.md
├── API_DOCUMENTATION.md
├── README.md
└── ...
```

---

## 🛠️ Tecnologías

### Backend
- **Spring Boot 3.5.6** - Framework principal
- **Spring Web MVC** - Manejo de solicitudes HTTP
- **Thymeleaf** - Motor de plantillas
- **Maven** - Gestión de dependencias y construcción
- **Java 17+** - Lenguaje de programación

### Frontend
- **HTML5** - Estructura
- **CSS3** - Estilos personalizados
- **JavaScript** - Interactividad
- **Bootstrap/Custom CSS** - Diseño responsive

### Documentación API
- **SpringDoc OpenAPI 2.6.0** - Swagger integrado
- **OpenAPI 3.0** - Especificación de API

---

## 📞 Endpoints Principales

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/` | Página principal |
| GET | `/menu` | Ver menú |
| POST | `/login` | Login de usuario |
| POST | `/registro` | Registro de usuario |
| POST | `/menu/carrito/agregar` | Agregar al carrito |
| GET | `/menu/carrito` | Ver carrito |
| POST | `/menu/confirmarCompra` | Procesar compra |

📖 **Para la lista completa de endpoints, ver `API_DOCUMENTATION.md`**

---

## 🔐 Seguridad

- ✅ Contraseñas hasheadas
- ✅ Validación de formularios
- ✅ Sesiones seguras
- ✅ Validación de datos en servidor

---

## 🐛 Solución de Problemas

### La aplicación no inicia
```bash
# Limpiar caché
mvn clean

# Reconstruir
mvn build

# Ejecutar
mvn spring-boot:run
```

### Puerto 8081 en uso
```bash
# Cambiar puerto en application.properties
server.port=8082
```

### Problemas con Maven
```bash
# Actualizar Maven
mvn -version

# Descargar dependencias nuevamente
mvn dependency:resolve
```

---

## 📊 Información Técnica

**Versión:** 1.0  
**Estado:** Activo ✅  
**Última actualización:** Diciembre 2025  

---

## 👥 Autores

- **Juan Pérez** - Développeur
- **Elmer** - Développeur

---

## 📄 Licencia

Este proyecto está bajo licencia MIT. Ver detalles en el archivo LICENSE.

---

## 🤝 Contribuciones

Las contribuciones son bienvenidas. Por favor:

1. Fork el repositorio
2. Crea una rama para tu feature
3. Commit tus cambios
4. Push a la rama
5. Abre un Pull Request

---

## 📧 Contacto

Para preguntas o soporte:
- 📧 Email: desarrollo@taqueriafrankelen.com
- 🐙 GitHub: [Elmer295](https://github.com/Elmer295)
- 💼 LinkedIn: [Contacto](https://www.linkedin.com)

---

## 🙏 Agradecimientos

- Spring Boot team por el excelente framework
- SpringDoc por la integración con Swagger
- Comunidad de desarrollo Java

---

**¡Disfruta tu Taquería Franken! 🌮**
