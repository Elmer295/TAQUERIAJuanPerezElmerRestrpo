package elmerotacos.tdea.service;

import org.springframework.stereotype.Service;
import java.util.List;

import elmerotacos.tdea.model.CarritoModel;
import elmerotacos.tdea.model.TacoModel;
import elmerotacos.tdea.repository.CarritoRepository;
import elmerotacos.tdea.repository.TacoRepository;

@Service
public class CarritoService {

    private final CarritoRepository carritoRepository;
    private final TacoRepository tacoRepository;

    public CarritoService(CarritoRepository carritoRepository, TacoRepository tacoRepository) {
        this.carritoRepository = carritoRepository;
        this.tacoRepository = tacoRepository;
    }

    // -----------------------------------------------------
    // GUARDAR O ACTUALIZAR ITEM
    // -----------------------------------------------------
    public CarritoModel guardar(CarritoModel item) {
        return carritoRepository.save(item);
    }

    // -----------------------------------------------------
    // OBTENER TODOS LOS ITEMS DEL CARRITO
    // -----------------------------------------------------
    public List<CarritoModel> obtenerTodo() {
        return carritoRepository.findAll();
    }

    // -----------------------------------------------------
    // BUSCAR ITEM POR ID
    // -----------------------------------------------------
    public CarritoModel obtenerPorId(Long id) {
        return carritoRepository.findById(id).orElse(null);
    }

    // -----------------------------------------------------
    // ELIMINAR ITEM POR ID
    // -----------------------------------------------------
    public void eliminarItem(Long id) {
        carritoRepository.deleteById(id);
    }

    // -----------------------------------------------------
    // VACIAR CARRITO
    // -----------------------------------------------------
    public void vaciarCarrito() {
        carritoRepository.deleteAll();
    }

    // -----------------------------------------------------
    // OBTENER TACO POR ID
    // -----------------------------------------------------
    public TacoModel obtenerTacoPorId(Long id) {
        return tacoRepository.findById(id).orElse(null);
    }

    // -----------------------------------------------------
    // AGREGAR ITEM AL CARRITO CON GETTERS Y SETTERS CORRECTOS
    // -----------------------------------------------------
    public void agregarItem(TacoModel taco) {

        // Buscar si ya existe un item con este taco
        List<CarritoModel> carrito = carritoRepository.findAll();

        for (CarritoModel item : carrito) {
            if (item.getTacoModel().getId().equals(taco.getId())) {

                // Ya existe → incrementar cantidad
                item.setCantidad(item.getCantidad() + 1);
                carritoRepository.save(item);
                return;
            }
        }

        // Si no existe → crear uno nuevo
        CarritoModel nuevoItem = new CarritoModel();

        nuevoItem.setOpcion(taco);        // set del TacoModel
        nuevoItem.setPrecio(taco.getPrecio());
        nuevoItem.setCantidad(1);

        nuevoItem.setUsuario("0001");     // Temporal mientras no hay login

        carritoRepository.save(nuevoItem);
    }
}