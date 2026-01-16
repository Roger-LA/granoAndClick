package com.granoAndClick.granoAndClick.controller;

import java.util.Calendar;
import java.util.Date;

import com.granoAndClick.granoAndClick.dto.LoginDTO;
import com.granoAndClick.granoAndClick.dto.Token;
import com.granoAndClick.granoAndClick.model.Usuarios;
import com.granoAndClick.granoAndClick.service.UsuariosService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.ServletException;
import java.security.Key;
import java.time.LocalDate;
import java.time.ZoneId;


@RestController
@RequestMapping("/api/login")
public class LoginController {

    
	private final UsuariosService service;
	private final Key jwtKey;
	
	@Autowired
	public LoginController(UsuariosService service, Key jwtKey) {
		this.service = service;
		this.jwtKey = jwtKey;
	}
	
	
	@PostMapping
	public Token ingresar(@RequestBody Usuarios usuario) throws ServletException {
	    if (service.validateUser(usuario)) {
	        Usuarios user = service.getByCorreo(usuario.getCorreo());
	        if(user!=null) {
		        String role = user.getTiposUsuario().getNombre(); // "admin" o "user"
		        
		        Date expiration = Date.from(LocalDate.now().plusDays(1) 
		        		.atStartOfDay(ZoneId.systemDefault()).toInstant());
		        String jwt = Jwts.builder()
		                .setSubject(user.getCorreo())
		                .claim("role", role)
		                .setExpiration(expiration) //24h
		                .signWith(jwtKey, SignatureAlgorithm.HS256) 
		                .compact();

		        return new Token(jwt);
	        }
	    }
	    throw new ServletException("Nombre de usuario o contraseña incorrectos");
	}

	private String generateToken(String email) {
		Calendar calendar = Calendar.getInstance();//fecha y hora
		//calendar.add(Calendar.MINUTE, 30);
		calendar.add(Calendar.HOUR,24);
		 return Jwts.builder().setSubject(email)
					  .claim("role","user")
					  .setIssuedAt(new Date())
					  .setExpiration(calendar.getTime())
					  .signWith(jwtKey, SignatureAlgorithm.HS256)
					  .compact();
		
	}
	
	@GetMapping("/sesion") //Sesion
	public LoginDTO sesion(Authentication auth) {
		String correo = auth.getName();
		Usuarios user = service.getByCorreo(correo);
		if(user == null) {
			throw new RuntimeException("Usuario no encontrado");
		}//if
		
		Long tipoId = (user.getTiposUsuario() != null) ? user.getTiposUsuario().getId() : null;
		String tipoNombre = (user.getTiposUsuario() != null) ? user.getTiposUsuario().getNombre() : null;
		
		return new LoginDTO(
				user.getUsuarioId(),
				user.getNombres(),
				user.getCorreo(),
				tipoId,
				tipoNombre,
				user.getSubindice()
				);
	}//GetMapping
	
}
