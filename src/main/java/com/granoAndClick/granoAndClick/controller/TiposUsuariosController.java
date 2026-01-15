package com.granoAndClick.granoAndClick.controller;

import java.util.List;

import com.granoAndClick.granoAndClick.model.TiposUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.granoAndClick.granoAndClick.service.TiposUsuariosService;

@RestController
@RequestMapping("/api/tiposUsuarios")
public class TiposUsuariosController {

	private final TiposUsuariosService service;

	
	
	@Autowired
	public TiposUsuariosController(TiposUsuariosService service) {
		this.service = service;
	}
	
	@GetMapping
	public List<TiposUsuarios> getAllTiposUsuarios() {
	    return service.getAllTiposUsuarios();
	}
	@GetMapping("{tipoId}")
	public TiposUsuarios geTiposUsuarios(@PathVariable("tipoId") Long id) {
	    return service.getTiposUsuarios(id);
	}
	
	@DeleteMapping(path = "{tipoUsuarioId}")
	public TiposUsuarios deleteCategoria(@PathVariable("tipoUsuarioId") Long id) {
	    return service.deleteTiposUsuarios(id);
	}
	
	@PostMapping
	public TiposUsuarios addCategoria(@RequestBody TiposUsuarios TiposUsuarios) {
	    return service.addTiposUsuarios(TiposUsuarios);
	}
	
	@PutMapping(path = "{tipoUsuarioId}")
	public TiposUsuarios updateCategoria(
	    @PathVariable("tipoUsuarioId") Long id,
	    @RequestParam(name = "nombre", required = false) String nombre,
	    @RequestParam(name = "descripcion", required = false) String descripcion
	) {
	    return service.updateTiposUsuarios(id, nombre, descripcion);
	}
	
}
