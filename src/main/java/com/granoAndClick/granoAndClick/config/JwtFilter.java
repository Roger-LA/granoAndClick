package com.granoAndClick.granoAndClick.config;

import java.io.IOException;
import java.security.Key;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

public class JwtFilter extends GenericFilterBean {

    private final Key key;

    public JwtFilter(Key key) {
        this.key = key;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String authHeader = httpServletRequest.getHeader("Authorization");
        String method = httpServletRequest.getMethod();
        String URI = httpServletRequest.getRequestURI();

        if (method.equalsIgnoreCase("OPTIONS")) {
            chain.doFilter(request, response);
            return;
        }

        // Rutas que no requieren token obligatoriamente
        boolean requiereToken = !(URI.startsWith("/api/login") || URI.startsWith("/api/usuarios"));

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            try {
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(key)
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

                String email = claims.getSubject();
                String role = claims.get("role", String.class);

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                email,
                                null,
                                List.of(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()))
                        );

                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (ExpiredJwtException | MalformedJwtException | SignatureException e) {
                throw new ServletException("Invalid Token: " + e.getMessage());
            }
        } else if (requiereToken) {
            // Si la ruta requiere token y no se envió es error
            throw new ServletException("Invalid Token: missing or malformed Authorization header");
        }

        chain.doFilter(request, response);
    }
}
