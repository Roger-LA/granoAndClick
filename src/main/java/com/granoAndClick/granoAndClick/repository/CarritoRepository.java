package com.granoAndClick.granoAndClick.repository;

import com.granoAndClick.granoAndClick.model.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long> {

}