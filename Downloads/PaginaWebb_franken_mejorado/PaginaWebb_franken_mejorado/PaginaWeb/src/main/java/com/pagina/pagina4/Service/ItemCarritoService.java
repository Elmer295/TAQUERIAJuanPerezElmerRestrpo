package com.pagina.pagina4.Service;

import com.pagina.pagina4.Model.ItemCarrito;
import com.pagina.pagina4.Model.Usuario;
import com.pagina.pagina4.Repository.ItemCarritoRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemCarritoService {

    private final ItemCarritoRepository itemCarritoRepository;

    public ItemCarritoService(ItemCarritoRepository itemCarritoRepository) {
        this.itemCarritoRepository = itemCarritoRepository;
    }

    // Obtener todos los ítems del carrito de un usuario
    public List<ItemCarrito> obtenerCarritoPorUsuario(Usuario usuario) {
        return itemCarritoRepository.findByUsuario(usuario);
    }

    // Agregar un ítem al carrito
    public ItemCarrito agregarItem(ItemCarrito item) {
        return itemCarritoRepository.save(item);
    }

    // Eliminar un ítem del carrito por ID
    public void eliminarItem(Long id) {
        itemCarritoRepository.deleteById(id);
    }

    // Eliminar todos los ítems de un usuario (cancelar carrito)
    public void eliminarCarrito(Usuario usuario) {
        itemCarritoRepository.deleteByUsuario(usuario);
    }
}