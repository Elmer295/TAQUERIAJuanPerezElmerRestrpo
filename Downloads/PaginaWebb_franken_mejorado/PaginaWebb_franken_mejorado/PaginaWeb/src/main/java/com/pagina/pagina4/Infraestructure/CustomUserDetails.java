package com.pagina.pagina4.Infraestructure;
import com.pagina.pagina4.Model.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
public class CustomUserDetails implements UserDetails {
       private final Usuario usuario;
    private final String role; // "ROLE_ADMIN" o "ROLE_USER"

    public CustomUserDetails(Usuario usuario, String role) {
        this.usuario = usuario;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return usuario.getContrasena();
    }

    @Override
    public String getUsername() {
        // Spring Security usará "correo" como username
        return usuario.getCorreo();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }

    // helper para acceder al Usuario real si lo necesitas
    public Usuario getUsuario() {
        return usuario;
    }
    
}
