# Documentación Swagger - Taquería Franken

## Sobre la Documentación API

Tu aplicación ahora incluye documentación automática con **Swagger/OpenAPI 3.0** usando **SpringDoc-OpenAPI**. Esto proporciona una interfaz interactiva para explorar todos los endpoints de tu API.

## Acceso a Swagger UI

Una vez que la aplicación está en ejecución, puedes acceder a la documentación en:

```
http://localhost:8081/swagger-ui.html
```

También puedes acceder a la especificación JSON de OpenAPI en:

```
http://localhost:8081/api-docs
```

## Características de Swagger

La documentación incluye:

✅ **Descripción completa de todos los controladores**
- HomeController - Páginas principales
- UsuarioController - Autenticación (login, registro, logout)
- OpcionController - Menú y carrito de compras
- TacosController - Gestión de tacos (crear, eliminar)

✅ **Para cada endpoint tienes:**
- Descripción clara del propósito
- Parámetros requeridos
- Respuestas posibles (códigos HTTP)
- Ejemplos de uso

✅ **Interfaz interactiva:**
- Puedes probar los endpoints directamente desde el navegador
- Visualizar esquemas de request/response
- Filtrar por tags/categorías

## Controladores Documentados

### 1. **HomeController** - Páginas Principales
- `GET /` - Redirige a login
- `GET /index` - Página de inicio
- `GET /mision` - Página de misión
- `GET /vision` - Página de visión
- `GET /objetivo` - Página de objetivos
- `GET /ubicacion` - Ubicación de la taquería
- `GET /contacto` - Página de contacto

### 2. **UsuarioController** - Autenticación
- `GET /registro` - Formulario de registro
- `POST /registro` - Procesar registro
- `GET /login` - Formulario de login
- `POST /login` - Procesar login
- `GET /logout` - Cerrar sesión

### 3. **OpcionController** - Menú y Carrito
**Menú:**
- `GET /menu` - Ver menú de tacos

**Carrito:**
- `POST /menu/carrito/agregar` - Agregar taco al carrito
- `GET /menu/carrito` - Ver carrito
- `POST /menu/carrito/editar` - Actualizar cantidades
- `GET /menu/carrito/eliminar/{id}` - Eliminar item
- `GET /menu/carrito/cancelar` - Cancelar pedido

**Pago:**
- `GET /menu/confirmarCompra` - Mostrar formulario de pago
- `POST /menu/confirmarCompra` - Procesar compra

### 4. **TacosController** - Gestión de Tacos
- `GET /menu/tacos/nuevo` - Formulario nuevo taco
- `POST /menu/tacos/guardar` - Guardar taco
- `GET /menu/tacos/eliminar/{id}` - Eliminar taco
- `POST /menu/tacos/eliminar/{id}` - Eliminar taco (POST)

## Instalación de Dependencias

Ya está agregada la dependencia de SpringDoc-OpenAPI en el `pom.xml`:

```xml
<!-- Swagger/SpringDoc OpenAPI para documentación de API -->
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.6.0</version>
</dependency>
```

## Configuración

La configuración de Swagger está en dos lugares:

### 1. **SwaggerConfig.java** (Configuración avanzada)
```java
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        // Configuración personalizada de OpenAPI
    }
}
```

### 2. **application.properties** (Propiedades de Swagger)
```properties
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.swagger-ui.enabled=true
```

## Anotaciones Utilizadas

Cada endpoint está documentado con anotaciones de Swagger:

```java
@Operation(summary = "...", description = "...")
@ApiResponse(responseCode = "200", description = "...")
@ApiResponses(value = {...})
@Tag(name = "...", description = "...")
```

## Prueba de los Endpoints

1. Abre tu navegador en: `http://localhost:8081/swagger-ui.html`
2. Verás todos los endpoints agrupados por tags (categorías)
3. Haz clic en cualquier endpoint para ver detalles
4. Usa el botón "Try it out" para probar
5. Completa los parámetros requeridos
6. Haz clic en "Execute" para enviar la solicitud

## Próximas Mejoras (Opcional)

Si deseas mejorar aún más la documentación, considera:

- 📌 Agregar `@RequestBody` con ejemplos en los endpoints POST
- 📌 Documentar los modelos (Usuario, Opcion, ItemCarrito) con `@Schema`
- 📌 Configurar perfiles de Swagger para diferentes ambientes
- 📌 Integrar autenticación JWT en Swagger

## Notas Importantes

⚠️ **La documentación se genera automáticamente** a partir de:
- Las anotaciones en los controladores
- Los tipos de retorno de los métodos
- Los parámetros de los métodos
- La configuración de Swagger

⚠️ **Mantén actualizada la documentación** cuando:
- Agregues nuevos endpoints
- Cambies parámetros o respuestas
- Modifiques la lógica de validación

---

**¡Tu API ahora está completamente documentada con Swagger! 🎉**
