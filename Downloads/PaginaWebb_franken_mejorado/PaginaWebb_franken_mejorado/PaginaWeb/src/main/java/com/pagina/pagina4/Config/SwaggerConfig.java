package com.pagina.pagina4.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Taquería Franken")
                        .version("1.0.0")
                        .description("Documentación completa de la API de la Taquería Franken. " +
                                "Sistema de gestión de tacos, usuarios y carrito de compras.")
                        .contact(new Contact()
                                .name("Soporte Técnico")
                                .email("soporte@taqueria-franken.com")
                                .url("https://www.taqueria-franken.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")));
    }
}
