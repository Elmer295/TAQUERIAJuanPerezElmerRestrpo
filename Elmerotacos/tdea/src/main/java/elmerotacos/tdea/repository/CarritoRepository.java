package elmerotacos.tdea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import elmerotacos.tdea.model.CarritoModel;
@Repository
public interface CarritoRepository extends JpaRepository<CarritoModel, Long> {
    
      

    // Borrar todos los ítems de un usuario (para cancelar pedido)
   
}


