package com.pagina.pagina4.Infraestructure.Config;

import com.pagina.pagina4.Infraestructure.CustomUserDetails;
import com.pagina.pagina4.Service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    // correo del admin (constante centralizada)
    public static final String ADMIN_EMAIL = "admin@elmerotaco.com";

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/", "/index", "/login", "/registro",
                    "/css/**", "/js/**", "/images/**", "/webjars/**",
                    "/mision", "/vision", "/objetivo", "/contacto", "/ubicacion", "/seleccion",
                    "/menu", "/menu/**", "/Documentacion"
                ).permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/menu/**", "/carrito/**", "/confirmarCompra", "/checkout")
                .authenticated()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
    .loginPage("/login")
    .usernameParameter("correo")
    .passwordParameter("contrasena")
    .successHandler((request, response, authentication) -> {
    CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
    request.getSession().setAttribute("usuarioLogueado", userDetails.getUsuario());
    response.sendRedirect("/index");
})

    .permitAll()
)
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
            );
            

        return http.build();
    }

 

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
}
