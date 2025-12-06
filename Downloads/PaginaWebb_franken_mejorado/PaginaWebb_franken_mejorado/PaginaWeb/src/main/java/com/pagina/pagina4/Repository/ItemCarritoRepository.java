package com.pagina.pagina4.Repository;

import com.pagina.pagina4.Model.ItemCarrito;
import com.pagina.pagina4.Model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemCarritoRepository extends JpaRepository<ItemCarrito, Long> {

    // Obtener todos los ítems del carrito de un usuario
    List<ItemCarrito> findByUsuario(Usuario usuario);

    // Borrar todos los ítems de un usuario (para cancelar pedido)
    void deleteByUsuario(Usuario usuario);
}