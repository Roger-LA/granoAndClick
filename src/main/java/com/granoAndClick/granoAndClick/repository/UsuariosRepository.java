package com.granoAndClick.granoAndClick.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.granoAndClick.granoAndClick.model.Usuarios;

public interface UsuariosRepository extends JpaRepository <Usuarios, Long>{
	Optional<Usuarios> findByNombres (String nombres);

}
