package com.pagina.pagina4.Service;
import com.pagina.pagina4.Infraestructure.CustomUserDetails;
import com.pagina.pagina4.Model.Usuario;
import com.pagina.pagina4.Repository.UsuarioRepository;
import com.pagina.pagina4.Infraestructure.Config.SecurityConfig;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
      private final UsuarioRepository usuarioRepository;

    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByCorreo(correo);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado con correo: " + correo);
        }

        // Asignación dinámica de rol: si su correo coincide con el admin configurado -> ADMIN
        String role = SecurityConfig.ADMIN_EMAIL.equalsIgnoreCase(usuario.getCorreo())
                      ? "ROLE_ADMIN"
                      : "ROLE_USER";

        return new CustomUserDetails(usuario, role);
    }

}