package com.pagina.pagina4.Controller;

import com.pagina.pagina4.Model.Usuario;
import com.pagina.pagina4.Service.UsuarioService;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

/**
 * Controlador para la gestión de usuarios (registro, login, logout)
 */
@Controller
@Tag(name = "Usuarios", description = "Endpoints para autenticación y gestión de usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Mostrar formulario de registro
    @GetMapping("/registro")
    @Operation(summary = "Mostrar formulario de registro", 
               description = "Muestra la página con el formulario para registrar un nuevo usuario")
    @ApiResponse(responseCode = "200", description = "Formulario de registro mostrado exitosamente")
    public String mostrarRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    // Procesar registro de usuario
    @PostMapping("/registro")
    @Operation(summary = "Registrar nuevo usuario", 
               description = "Procesa el registro de un nuevo usuario en la plataforma")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "302", description = "Registro exitoso, redirige a /index"),
        @ApiResponse(responseCode = "200", description = "Error en el registro - correo ya existe")
    })
    public String registrarUsuario(@ModelAttribute Usuario usuario, HttpSession session, Model model) {
        Usuario registrado = usuarioService.registrarUsuario(usuario);
        if (registrado == null) {
            model.addAttribute("error", "El correo ya está registrado 😅");
            return "registro";
        }

        session.setAttribute("usuarioLogueado", registrado);
        return "redirect:/index";
    }

    // Mostrar formulario de login
    @GetMapping("/login")
    @Operation(summary = "Mostrar formulario de login", 
               description = "Muestra la página con el formulario para iniciar sesión")
    @ApiResponse(responseCode = "200", description = "Formulario de login mostrado exitosamente")
    public String mostrarLogin(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Correo o contraseña incorrectos 😅");
        }
        return "login";
    }

   
   

    // Cerrar sesión
    @GetMapping("/logout")
    @Operation(summary = "Cerrar sesión", 
               description = "Invalida la sesión del usuario y lo redirige a la página de login")
    @ApiResponse(responseCode = "302", description = "Sesión cerrada, redirige a /login")
    public String cerrarSesion(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}