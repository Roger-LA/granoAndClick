package com.granoAndClick.granoAndClick.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.github.cdimascio.dotenv.Dotenv;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    Dotenv dotenv = Dotenv.load();

    private String allowedOrigins = dotenv.get("ALLOWED_ORIGINS");
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/usuarios")
        .allowedOrigins(allowedOrigins.split(","))
        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
        
    	registry.addMapping("/api/tiposUsuarios")
                .allowedOrigins(allowedOrigins.split(","))
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");

        registry.addMapping("/api/productos")
                .allowedOrigins(allowedOrigins.split(","))
                .allowedMethods("GET", "POST", "PUT", "DELETE");

        registry.addMapping("/api/pedidos")
                .allowedOrigins(allowedOrigins.split(","))
                .allowedMethods("GET", "POST", "PUT", "DELETE");

        registry.addMapping("/api/pedidosDetalle")
                .allowedOrigins(allowedOrigins.split(","))
                .allowedMethods("GET", "POST", "PUT", "DELETE");

        registry.addMapping("/api/login")
                .allowedOrigins(allowedOrigins.split(","))
                .allowedMethods("POST");

        registry.addMapping("/api/contactos")
                .allowedOrigins(allowedOrigins.split(","))
                .allowedMethods("GET", "POST", "PUT", "DELETE");

        registry.addMapping("/api/carritoDetalle")
                .allowedOrigins(allowedOrigins.split(","))
                .allowedMethods("GET", "POST", "PUT", "DELETE");

        registry.addMapping("/api/carrito")
                .allowedOrigins(allowedOrigins.split(","))
                .allowedMethods("GET", "POST", "PUT", "DELETE");

        registry.addMapping("/api/usuarios/recuperar")
        .allowedOrigins(allowedOrigins.split(","))
        .allowedMethods("PUT");
    }
}


