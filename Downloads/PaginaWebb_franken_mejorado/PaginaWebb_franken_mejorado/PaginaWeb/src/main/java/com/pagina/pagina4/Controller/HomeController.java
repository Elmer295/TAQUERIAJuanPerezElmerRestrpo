package com.pagina.pagina4.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * Controlador para las páginas principales de la aplicación
 */
@Controller
@Tag(name = "Home", description = "Endpoints para las páginas principales de la Taquería")
public class HomeController {

    @GetMapping("/")
    @Operation(summary = "Redirige a la página de login", 
               description = "Redirige automáticamente a la página de inicio de sesión")
    @ApiResponse(responseCode = "302", description = "Redireccionamiento a /login")
    public String raiz() {
        return "redirect:/login";
    }

    @GetMapping("/index")
    @Operation(summary = "Mostrar página principal", 
               description = "Muestra la página de inicio principal de la taquería")
    @ApiResponse(responseCode = "200", description = "Página de inicio mostrada exitosamente")
    public String index() {
        return "index";
    }

    @GetMapping("/mision")
    @Operation(summary = "Mostrar página de misión", 
               description = "Muestra la página con la misión de la taquería")
    @ApiResponse(responseCode = "200", description = "Página de misión mostrada exitosamente")
    public String mision() {
        return "mision";
    }

    @GetMapping("/ubicacion")
    @Operation(summary = "Mostrar página de ubicación", 
               description = "Muestra la página con la ubicación de la taquería")
    @ApiResponse(responseCode = "200", description = "Página de ubicación mostrada exitosamente")
    public String ubicacion() {
        return "ubicacion";
    }

    @GetMapping("/contacto")
    @Operation(summary = "Mostrar página de contacto", 
               description = "Muestra la página de contacto de la taquería")
    @ApiResponse(responseCode = "200", description = "Página de contacto mostrada exitosamente")
    public String contacto() {
        return "contacto";
    }

    @GetMapping("/vision")
    @Operation(summary = "Mostrar página de visión", 
               description = "Muestra la página con la visión de la taquería")
    @ApiResponse(responseCode = "200", description = "Página de visión mostrada exitosamente")
    public String vision() {
        return "vision";
    }

    @GetMapping("/objetivo")
    @Operation(summary = "Mostrar página de objetivos", 
               description = "Muestra la página con los objetivos de la taquería")
    @ApiResponse(responseCode = "200", description = "Página de objetivos mostrada exitosamente")
    public String objetivo() {
        return "objetivo";
    }
}
