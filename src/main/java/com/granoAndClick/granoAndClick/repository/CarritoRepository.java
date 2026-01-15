package com.granoAndClick.granoAndClick.repository;

import com.granoAndClick.granoAndClick.model.Carrito;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.time.LocalDateTime;


@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long> {
	Optional<Carrito> findByFechaAgregado(LocalDateTime fechaAgregado);
}