package com.pagina.pagina4.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pagina.pagina4.Model.Opcion;
import com.pagina.pagina4.Repository.OpcionRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

/**
 * Controlador para la gestión de tacos (crear y eliminar tacos del menú)
 */
@Controller
@RequestMapping("/menu/tacos")
@Tag(name = "Gestión de Tacos", description = "Endpoints para crear, editar y eliminar tacos del menú")
public class TacosController {
    
    @Autowired
    private OpcionRepository opcionRepository;

    @GetMapping("/nuevo")
    @Operation(summary = "Mostrar formulario para nuevo taco", 
               description = "Muestra el formulario para crear un nuevo taco en el menú")
    @ApiResponse(responseCode = "200", description = "Formulario mostrado exitosamente")
    public String mostrarFormularioNuevoTaco(Model model) {
        model.addAttribute("taco", new Opcion());  
        return "tacos/nuevo-taco";  
    }

    @PostMapping("/guardar")
    @Operation(summary = "Guardar nuevo taco", 
               description = "Guarda un nuevo taco en la base de datos con su información de precios y descripción")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "302", description = "Taco guardado exitosamente, redirige a /menu"),
        @ApiResponse(responseCode = "400", description = "Error al procesar los datos")
    })
    public String guardarTaco(@ModelAttribute("taco") Opcion opcion) {
        opcionRepository.save(opcion);
        return "redirect:/menu";  
    }

    @GetMapping("/eliminar/{id}")
    @Operation(summary = "Eliminar taco por ID", 
               description = "Elimina un taco específico del menú")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "302", description = "Taco eliminado exitosamente, redirige a /menu"),
        @ApiResponse(responseCode = "404", description = "Taco no encontrado")
    })
    public String eliminarTaco(@PathVariable Long id) {
        opcionRepository.deleteById(id);
        return "redirect:/menu";
    }

    @PostMapping("/eliminar/{id}")
    @Operation(summary = "Eliminar taco por ID (POST)", 
               description = "Elimina un taco específico del menú usando método POST")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "302", description = "Taco eliminado exitosamente, redirige a /menu"),
        @ApiResponse(responseCode = "404", description = "Taco no encontrado")
    })
    public String borrarTaco(@PathVariable Long id) {
        opcionRepository.deleteById(id);
        return "redirect:/menu";
    }
}



