package com.pagina.pagina4.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pagina.pagina4.Model.Opcion;

@Repository
public interface OpcionRepository extends JpaRepository<Opcion, Long> {

    // Opcional: buscar opción por nombre si lo necesitas
    Opcion findByNombre(String nombre);
    
}