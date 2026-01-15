package com.granoAndClick.granoAndClick.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.granoAndClick.granoAndClick.model.CarritoDetalle;

@Repository
public interface CarritoDetalleRepository extends JpaRepository<CarritoDetalle, Long> {
    
    //List<CarritoDetalle> findByCarritoId(Long carritoId);
}