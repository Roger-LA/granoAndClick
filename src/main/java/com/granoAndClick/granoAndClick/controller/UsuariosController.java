package com.granoAndClick.granoAndClick.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.granoAndClick.granoAndClick.dto.ChangePassword;
import com.granoAndClick.granoAndClick.dto.RegistrarUsuarioDTO;
import com.granoAndClick.granoAndClick.model.Usuarios;
import com.granoAndClick.granoAndClick.service.UsuariosService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@RestController
@RequestMapping(path="/api")
@CrossOrigin(origins = "*")
public class UsuariosController {
	private final UsuariosService uService;
	
	@Autowired
	public UsuariosController (UsuariosService uService) {
		this.uService = uService;
	}
	
	@GetMapping("/usuarios/")
	public List<Usuarios> getUsuario(){
		return uService.getUsuarios();
	} 
	
	@GetMapping("/usuario/{userid}")
	public Usuarios getUsuario(@PathVariable ("userid") long id) {
		return uService.getUsuario(id);
	}
	
	@PostMapping("/usuarios")
	public Usuarios addUsuario(@RequestBody RegistrarUsuarioDTO dto) {
	    var auth = SecurityContextHolder.getContext().getAuthentication();

	    // Validar si se intenta registrar un admin
	    if (dto.getTipoUsuarioId() != null && dto.getTipoUsuarioId() == 1L) {
	    	if (auth == null || auth.getAuthorities().stream() 
	    			.noneMatch(a -> a
	    					.getAuthority().equals("ROLE_ADMIN"))) { 
	    		throw new RuntimeException("Solo un admin puede registrar otro admin");
	    		}
	    }

	    Usuarios nuevo = uService.addUsuarios(dto);

	    if (nuevo == null) {
	        throw new RuntimeException("El correo ya está registrado");
	    }

	    return nuevo;
	}

	@DeleteMapping("/usuario/{userid}")
	public Usuarios deleteUsuario(@PathVariable ("userid") long id) {
		return uService.deleteUsuario(id);
	}
	
	@PutMapping(path="/usuario/{userid}")
	public Usuarios updateUsuario(@PathVariable("userid") Long id,
			@RequestBody ChangePassword changePassword) {
		return uService.updateUsuario(id,changePassword);
	}
}