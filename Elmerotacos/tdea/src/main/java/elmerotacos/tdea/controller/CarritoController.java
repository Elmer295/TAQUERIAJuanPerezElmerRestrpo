package elmerotacos.tdea.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import elmerotacos.tdea.model.CarritoModel;
import elmerotacos.tdea.model.TacoModel;
import elmerotacos.tdea.service.CarritoService;

@Controller
@RequestMapping("/menu/carrito")
public class CarritoController {

    private final CarritoService carritoService;

    public CarritoController(CarritoService carritoService) {
        this.carritoService = carritoService;
    }

    // Mostrar carrito
    @GetMapping
    public String verCarrito(Model model) {

        List<CarritoModel> carrito = carritoService.obtenerTodo();
        model.addAttribute("carrito", carrito);

        int total = carrito.stream()
                .mapToInt(item -> item.getPrecio() * item.getCantidad())
                .sum();

        model.addAttribute("total", total);

        return "carrito";
    }

    // Editar cantidades
    @PostMapping("/editar")
    public String editar(
            @RequestParam("id[]") List<Long> ids,
            @RequestParam("cantidad[]") List<Integer> cantidades
    ) {
        for (int i = 0; i < ids.size(); i++) {
            CarritoModel item = carritoService.obtenerPorId(ids.get(i));

            if (item != null) {
                item.setCantidad(cantidades.get(i));
                carritoService.guardar(item);
            }
        }

        return "redirect:/menu/carrito";
    }

    // Eliminar un item
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        carritoService.eliminarItem(id);
        return "redirect:/menu/carrito";
    }

    // Vaciar carrito
    @GetMapping("/cancelar")
    public String cancelar() {
        carritoService.vaciarCarrito();
        return "redirect:/menu/carrito";
    }

    // Agregar taco
    @GetMapping("/agregar/{id}")
    public String agregarAlCarrito(@PathVariable Long id) {

        TacoModel taco = carritoService.obtenerTacoPorId(id);

        if (taco != null) {
            carritoService.agregarItem(taco);
        }

        return "redirect:/menu";
    }
}