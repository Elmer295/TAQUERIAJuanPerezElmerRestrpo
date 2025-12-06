package com.pagina.pagina4.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.pagina.pagina4.Model.ItemCarrito;
import com.pagina.pagina4.Model.Opcion;
import com.pagina.pagina4.Model.Usuario;
import com.pagina.pagina4.Repository.ItemCarritoRepository;
import com.pagina.pagina4.Repository.OpcionRepository;

import jakarta.servlet.http.HttpSession;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

/**
 * Controlador principal para la gestión del menú de opciones, carrito de compras y procesamiento de pedidos
 */
@Controller
@RequestMapping("/menu")
@Tag(name = "Menú y Carrito", description = "Endpoints para gestionar el menú de tacos y el carrito de compras")
public class OpcionController {

    private final OpcionRepository opcionRepository;
    private final ItemCarritoRepository itemCarritoRepository;

    public OpcionController(OpcionRepository opcionRepository,
                            ItemCarritoRepository itemCarritoRepository) {
        this.opcionRepository = opcionRepository;
        this.itemCarritoRepository = itemCarritoRepository;
    }

    // ================== MENÚ ==================

    // Mostrar menú de tacos
    @GetMapping
    @Operation(summary = "Mostrar menú de tacos", 
               description = "Muestra el menú completo de tacos disponibles para seleccionar")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Menú mostrado exitosamente"),
        @ApiResponse(responseCode = "302", description = "Usuario no autenticado, redirige a /login")
    })
    public String mostrarOpciones(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) return "redirect:/login";

        List<Opcion> opciones = opcionRepository.findAll();
        model.addAttribute("opciones", opciones);
        return "seleccion";
    }

    @GetMapping("/")
    @Operation(summary = "Redirige al menú", 
               description = "Redirige a la página de login")
    @ApiResponse(responseCode = "302", description = "Redireccionamiento a /login")
    public String raiz() {
        return "redirect:/login";
    }

    // ================== CARRITO ==================

    // Agregar un taco al carrito (por unidad)
    @PostMapping("/carrito/agregar")
    @Operation(summary = "Agregar taco al carrito", 
               description = "Agrega un taco seleccionado con tamaño y cantidad al carrito del usuario")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "302", description = "Taco agregado exitosamente, redirige al menú"),
        @ApiResponse(responseCode = "200", description = "Error: parámetros inválidos o taco no encontrado"),
        @ApiResponse(responseCode = "302", description = "Usuario no autenticado, redirige a /login")
    })
    public String agregarAlCarrito(
            @RequestParam("idTaco") Long idTaco,
            @RequestParam("tamano") String tamano,
            @RequestParam("cantidad") Integer cantidad,
            HttpSession session,
            Model model) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) return "redirect:/login";

        if (idTaco == null || tamano == null || tamano.isEmpty()
                || cantidad == null || cantidad <= 0) {
            model.addAttribute("mensaje", "Debes seleccionar un tamaño y una cantidad válida.");
            model.addAttribute("opciones", opcionRepository.findAll());
            return "seleccion";
        }

        Opcion taco = opcionRepository.findById(idTaco).orElse(null);
        if (taco == null) {
            model.addAttribute("mensaje", "El taco seleccionado no existe.");
            model.addAttribute("opciones", opcionRepository.findAll());
            return "seleccion";
        }

        // Buscar si ya existe un item con el mismo taco y tamaño en el carrito
        List<ItemCarrito> carrito = itemCarritoRepository.findByUsuario(usuario);
        ItemCarrito existente = null;
        for (ItemCarrito item : carrito) {
            if (item.getOpcion().getId().equals(idTaco)
                    && item.getTamano().equalsIgnoreCase(tamano)) {
                existente = item;
                break;
            }
        }

        int precioUnitario;
        switch (tamano) {
            case "Pequeño":
                precioUnitario = taco.getPrecioPequeno();
                break;
            case "Extragrande":
                precioUnitario = taco.getPrecioGrande();
                break;
            default:
                precioUnitario = taco.getPrecioNormal();
                break;
        }

        if (existente != null) {
            existente.setCantidad(existente.getCantidad() + cantidad);
            existente.setPrecio(precioUnitario);
            itemCarritoRepository.save(existente);
        } else {
            ItemCarrito item = new ItemCarrito();
            item.setUsuario(usuario);
            item.setOpcion(taco);
            item.setTamano(tamano);
            item.setCantidad(cantidad);
            item.setPrecio(precioUnitario);
            itemCarritoRepository.save(item);
        }

        // Volvemos al menú, con el formulario limpio
        return "redirect:/menu";
    }

    // Ver carrito
    @GetMapping("/carrito")
    @Operation(summary = "Ver carrito de compras", 
               description = "Muestra el carrito de compras del usuario con el total a pagar")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Carrito mostrado exitosamente"),
        @ApiResponse(responseCode = "302", description = "Usuario no autenticado, redirige a /login")
    })
    public String verCarrito(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) return "redirect:/login";

        List<ItemCarrito> carrito = itemCarritoRepository.findByUsuario(usuario);
        model.addAttribute("carrito", carrito);

        int total = carrito.stream()
                .mapToInt(item -> item.getPrecio() * item.getCantidad())
                .sum();
        model.addAttribute("total", total);

        return "carrito";
    }

    // Actualizar cantidades y tamaños desde el carrito
    @PostMapping("/carrito/editar")
    @Operation(summary = "Actualizar items del carrito", 
               description = "Actualiza las cantidades y tamaños de los items en el carrito")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "302", description = "Items actualizados, redirige al carrito"),
        @ApiResponse(responseCode = "302", description = "Usuario no autenticado, redirige a /login")
    })
    public String editarCarrito(@RequestParam("id") List<Long> ids,
                                @RequestParam("tamano") List<String> tamanos,
                                @RequestParam("cantidad") List<Integer> cantidades,
                                HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) return "redirect:/login";

        for (int i = 0; i < ids.size(); i++) {
            ItemCarrito item = itemCarritoRepository.findById(ids.get(i)).orElse(null);
            if (item != null && item.getUsuario().getId().equals(usuario.getId())) {
                item.setCantidad(cantidades.get(i));
                item.setTamano(tamanos.get(i));

                Opcion taco = item.getOpcion();
                String tamanoSeleccionado = item.getTamano();

                int precio;
                switch (tamanoSeleccionado) {
                    case "Pequeño":
                        precio = taco.getPrecioPequeno();
                        break;
                    case "Extragrande":
                        precio = taco.getPrecioGrande();
                        break;
                    default:
                        precio = taco.getPrecioNormal();
                        break;
                }

                item.setPrecio(precio);
                itemCarritoRepository.save(item);
            }
        }

        return "redirect:/menu/carrito";
    }

    // Eliminar taco del carrito
    @GetMapping("/carrito/eliminar/{id}")
    @Operation(summary = "Eliminar item del carrito", 
               description = "Elimina un item específico del carrito de compras")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "302", description = "Item eliminado, redirige al carrito"),
        @ApiResponse(responseCode = "302", description = "Usuario no autenticado, redirige a /login")
    })
    public String eliminarItemCarrito(@PathVariable Long id, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) return "redirect:/login";

        ItemCarrito item = itemCarritoRepository.findById(id).orElse(null);
        if (item != null && item.getUsuario().getId().equals(usuario.getId())) {
            itemCarritoRepository.delete(item);
        }

        return "redirect:/menu/carrito";
    }

    // Cancelar pedido completo
    @GetMapping("/carrito/cancelar")
    @Operation(summary = "Cancelar pedido completo", 
               description = "Vacía completamente el carrito y cancela el pedido")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "302", description = "Pedido cancelado, redirige al menú"),
        @ApiResponse(responseCode = "302", description = "Usuario no autenticado, redirige a /login")
    })
    public String cancelarPedido(HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) return "redirect:/login";

        List<ItemCarrito> carrito = itemCarritoRepository.findByUsuario(usuario);
        itemCarritoRepository.deleteAll(carrito);

        return "redirect:/menu";
    }

    // ================== PAGO / CONFIRMACIÓN ==================

    // Mostrar formulario de pago
    @GetMapping("/confirmarCompra")
    @Operation(summary = "Mostrar formulario de pago", 
               description = "Muestra el formulario para confirmar el método de pago y completar la compra")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Formulario de pago mostrado exitosamente"),
        @ApiResponse(responseCode = "302", description = "Usuario no autenticado, redirige a /login")
    })
    public String mostrarFormularioPago(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) return "redirect:/login";

        List<ItemCarrito> carrito = itemCarritoRepository.findByUsuario(usuario);
        int total = carrito.stream()
                .mapToInt(item -> item.getPrecio() * item.getCantidad())
                .sum();

        model.addAttribute("carrito", carrito);
        model.addAttribute("total", total);
        model.addAttribute("usuario", usuario);
        return "confirmarCompra";
    }

    @PostMapping("/confirmarCompra")
    @Operation(summary = "Procesar compra", 
               description = "Procesa el pago y valida la información de la tarjeta si es necesario")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Compra completada exitosamente"),
        @ApiResponse(responseCode = "200", description = "Error: validación de datos fallida"),
        @ApiResponse(responseCode = "302", description = "Usuario no autenticado, redirige a /login")
    })
    public String procesarCompra(
            @RequestParam(required = false) String metodoPago,
            @RequestParam(required = false) String numeroTarjeta,
            @RequestParam(required = false) String fechaExp,
            @RequestParam(required = false) String codigo,
            HttpSession session,
            Model model) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuario == null) return "redirect:/login";

        List<ItemCarrito> carrito = itemCarritoRepository.findByUsuario(usuario);

        int total = carrito.stream()
                .mapToInt(item -> item.getPrecio() * item.getCantidad())
                .sum();

        if (carrito.isEmpty()) {
            model.addAttribute("mensaje", "Tu carrito está vacío 😅");
            return "carrito";
        }

        // Validación método de pago
        if (metodoPago == null || metodoPago.isEmpty()) {
            model.addAttribute("error", "Debes seleccionar un método de pago.");
            model.addAttribute("total", total);
            return "confirmarCompra";
        }

        // Validación tarjeta
        if (metodoPago.equals("tarjeta")) {

            if (numeroTarjeta == null || !numeroTarjeta.matches("\\d{16}")) {
                model.addAttribute("error", "Número de tarjeta inválido (16 dígitos).");
                model.addAttribute("total", total);
                return "confirmarCompra";
            }

            if (codigo == null || !codigo.matches("\\d{3}")) {
                model.addAttribute("error", "Código de seguridad inválido (3 dígitos).");
                model.addAttribute("total", total);
                return "confirmarCompra";
            }

            try {
                java.time.LocalDate fechaActual = java.time.LocalDate.now();
                java.time.YearMonth fechaTarjeta = java.time.YearMonth.parse(fechaExp);

                if (fechaTarjeta.isBefore(java.time.YearMonth.from(fechaActual))) {
                    model.addAttribute("error", "La tarjeta está vencida.");
                    model.addAttribute("total", total);
                    return "confirmarCompra";
                }

            } catch (Exception e) {
                model.addAttribute("error", "Formato de fecha inválido. Usa AAAA-MM.");
                model.addAttribute("total", total);
                return "confirmarCompra";
            }

            model.addAttribute("mensaje", "✅ Pago con tarjeta confirmado con éxito.");
        } else {
            model.addAttribute("mensaje", "💵 Pago en efectivo seleccionado. Se pagará contraentrega.");
        }

        // Limpiar carrito
        itemCarritoRepository.deleteAll(carrito);

        // 🔥 AGREGAR TODOS LOS DATOS NECESARIOS A LA FACTURA
        model.addAttribute("usuario", usuario);
        model.addAttribute("carrito", carrito);
        model.addAttribute("direccion", usuario.getDireccion());
        model.addAttribute("total", total);

        return "compraExitosa";
    }
}

  
