package com.granoAndClick.granoAndClick.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.granoAndClick.granoAndClick.dto.ChangePassword;
import com.granoAndClick.granoAndClick.model.Usuarios;
import com.granoAndClick.granoAndClick.service.UsuariosService;

@RestController
@RequestMapping(path="/api")
@CrossOrigin(origins = "https://roger-la.github.io")
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
	
	@PostMapping("/usuarios/")
	public Usuarios addUsuarios(@RequestBody Usuarios usuario) {
		return uService.addUsuarios(usuario);
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