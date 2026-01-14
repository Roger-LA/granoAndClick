package com.granoAndClick.granoAndClick.config;

import static org.springframework.security.config.Customizer.withDefaults;

import java.security.Key;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
		this.jwtKey = Keys.hmacShaKeyFor(secret.getBytes()); 
		}
	
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
	    return http
	    		.csrf(csrf -> csrf.disable())
	    		.authorizeHttpRequests(auth -> auth
	    				.requestMatchers("/api/login/**").permitAll() 
	    				.requestMatchers("/api/usuarios").permitAll() // registro normal abierto 
	    				.anyRequest().authenticated() // todo lo demás requiere token
	    				) 
	    				// Integrar el filtro JWT antes del filtro estándar 
	    				.addFilterBefore(new JwtFilter(jwtKey), UsernamePasswordAuthenticationFilter.class) 
	    				.httpBasic(withDefaults()) 
	    				.build();
	}//Configure
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}//Encoder
	
	@Bean public Key jwtKey() { 
		return this.jwtKey; 
		}
	

}
