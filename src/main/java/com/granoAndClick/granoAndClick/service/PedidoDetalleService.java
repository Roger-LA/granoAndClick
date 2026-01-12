package com.granoAndClick.granoAndClick.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.granoAndClick.granoAndClick.model.PedidoDetalle;
import com.granoAndClick.granoAndClick.repository.PedidoDetalleRepository;

@Service
public class PedidoDetalleService {

    private final PedidoDetalleRepository repository;

    @Autowired
    public PedidoDetalleService(PedidoDetalleRepository repository) {
        this.repository = repository;
    }

    public List<PedidoDetalle> getAllDetalles() {
        return repository.findAll();
    }

    public List<PedidoDetalle> getDetallesByPedido(Long pedidoId) {
        return repository.findByPedido_PedidoId(pedidoId);
    }

    public PedidoDetalle addDetalle(PedidoDetalle detalle) {
        return repository.save(detalle);
    }
    
    public PedidoDetalle updateDetalle(Long id, PedidoDetalle datosNuevos) {
        PedidoDetalle detalle = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Detalle no encontrado"));
        
        detalle.setCantidad(datosNuevos.getCantidad());
        detalle.setPrecioUnitario(datosNuevos.getPrecioUnitario());
        
        return repository.save(detalle);
    }

    public void deleteDetalle(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }
}
