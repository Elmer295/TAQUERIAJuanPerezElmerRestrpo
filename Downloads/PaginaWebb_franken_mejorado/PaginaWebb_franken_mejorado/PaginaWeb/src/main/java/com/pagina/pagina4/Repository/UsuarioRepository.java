package com.pagina.pagina4.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pagina.pagina4.Model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Buscar usuario por correo electrónico (para login)
    Usuario findByCorreo(String correo);

    // Verificar si un correo ya existe (para registro)
    boolean existsByCorreo(String correo);
}