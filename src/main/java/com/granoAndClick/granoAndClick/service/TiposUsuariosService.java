package com.granoAndClick.granoAndClick.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.query.NativeQuery.ReturnableResultNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.granoAndClick.granoAndClick.model.TiposUsuarios;
import com.granoAndClick.granoAndClick.repository.TiposUsuariosRepository;

@Service
public class TiposUsuariosService {

	private final TiposUsuariosRepository repository;
	
	
	@Autowired
	public TiposUsuariosService(TiposUsuariosRepository repository) {
		this.repository = repository;
	}

	public List<TiposUsuarios> getAllTiposUsuarios() {
		return repository.findAll();

	}
	
	

	public TiposUsuarios deleteTiposUsuarios(Long id) {
		TiposUsuarios tipos = null;
	    if (repository.existsById(id)) {
	        tipos = repository.findById(id).get();
	        repository.deleteById(id);
	    }
	    return tipos;
	}

	public TiposUsuarios addTiposUsuarios(TiposUsuarios tiposUsuarios) {
		 Optional<TiposUsuarios> tipos = repository.findByNombre(tiposUsuarios.getNombre());
		    if (tipos.isEmpty()) {
		        return repository.save(tiposUsuarios);
		    } else {
		        System.out.println("El tipo de usuario  " + tiposUsuarios.getNombre() + " ya esta agregado");
		    }
		    return null;
	}

	public TiposUsuarios updateTiposUsuarios(Long id, String nombre, String descripcion) {
		TiposUsuarios tipos = null;
		
		if (repository.existsById(id)) {
	        tipos = repository.findById(id).get();
	        if (nombre != null) tipos.setNombre(nombre);
	        if (descripcion != null) tipos.setDescripcion(descripcion);
	    }
	    return tipos;
	}

	public TiposUsuarios getTiposUsuarios(Long id) {
		return repository.findById(id).orElseThrow(
				()-> new IllegalArgumentException("El tipo usario  con el id "+id+" no existe")
				);
	}

}
