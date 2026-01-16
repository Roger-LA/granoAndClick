package com.granoAndClick.granoAndClick.config;

import static org.springframework.security.config.Customizer.withDefaults;

import java.security.Key;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.security.Keys;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final Key jwtKey;

    public SecurityConfig() {
        Dotenv dotenv = Dotenv.load();
        String secret = dotenv.get("JWT_SECRET");
        if (secret == null || secret.length() < 32) {
            throw new IllegalStateException("JWT_SECRET no definido o demasiado corto");
        }
        this.jwtKey = Keys.hmacShaKeyFor(secret.getBytes());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // rutas públicas
                		.requestMatchers(HttpMethod.POST, "/api/login").permitAll() 
                		.requestMatchers("/api/login").authenticated()
                		.requestMatchers(HttpMethod.POST,"/api/usuarios").permitAll()
                		.requestMatchers("/api/usuarios").authenticated()

                		.requestMatchers(HttpMethod.POST,"/api/contactos").permitAll()
                		.requestMatchers("/api/usuarios").authenticated()
                		
                		.requestMatchers(HttpMethod.PUT,"/api/usuarios/recuperar-password").permitAll()
       

                        // todo lo demás requiere token
                        .anyRequest().authenticated()
                )
                // integrar el filtro JWT
                .addFilterBefore(new JwtFilter(jwtKey), UsernamePasswordAuthenticationFilter.class)
                .httpBasic(withDefaults())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Key jwtKey() {
        return this.jwtKey;
    }
}
