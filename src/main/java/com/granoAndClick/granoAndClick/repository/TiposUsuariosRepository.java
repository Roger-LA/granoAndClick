package com.granoAndClick.granoAndClick.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.granoAndClick.granoAndClick.model.TiposUsuarios;


public interface TiposUsuariosRepository extends JpaRepository<TiposUsuarios,Long>{

	Optional<TiposUsuarios>findByNombre(String nombre);
}
