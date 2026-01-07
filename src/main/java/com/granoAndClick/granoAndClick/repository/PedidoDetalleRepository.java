package com.granoAndClick.granoAndClick.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.granoAndClick.granoAndClick.model.PedidoDetalle;

@Repository
public interface PedidoDetalleRepository extends JpaRepository<PedidoDetalle, Long> {
    
    List<PedidoDetalle> findByPedidoId(Long pedidoId);
}