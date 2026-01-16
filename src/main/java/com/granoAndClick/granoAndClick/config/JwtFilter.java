package com.granoAndClick.granoAndClick.config;

import java.io.IOException;
import java.security.Key;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtFilter extends OncePerRequestFilter {

    private final Key key;

    public JwtFilter(Key key) {
        this.key = key;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

    	String path = request.getRequestURI();
        String method = request.getMethod();

        // EXCEPCIÓN: Si es GET a productos o un preflight de CORS (OPTIONS), deja pasar sin validar Token
        if ("OPTIONS".equalsIgnoreCase(method) || 
           (path.startsWith("/api/productos") && "GET".equalsIgnoreCase(method))) {
            filterChain.doFilter(request, response);
            return;
        }
        
        String authHeader = request.getHeader("Authorization");

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
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Token: " + e.getMessage());
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
