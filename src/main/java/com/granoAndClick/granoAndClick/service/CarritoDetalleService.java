package com.granoAndClick.granoAndClick.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.granoAndClick.granoAndClick.repository.CarritoDetalleRepository;
import com.granoAndClick.granoAndClick.repository.CarritoRepository;
import com.granoAndClick.granoAndClick.repository.ProductoRepository;
import com.granoAndClick.granoAndClick.dto.CarritoDetalleDTO;
import com.granoAndClick.granoAndClick.model.Carrito;
import com.granoAndClick.granoAndClick.model.CarritoDetalle;
import com.granoAndClick.granoAndClick.model.Producto;

@Service
public class CarritoDetalleService {

    private final CarritoDetalleRepository repository;
    private final ProductoRepository productoRepository;
    private final CarritoRepository carritoRepository;

    @Autowired
    public CarritoDetalleService(CarritoDetalleRepository repository,
                                 ProductoRepository productoRepository,
                                 CarritoRepository carritoRepository) {
        this.repository = repository;
        this.productoRepository = productoRepository;
        this.carritoRepository = carritoRepository;
    }

    public List<CarritoDetalle> getAllCarritoDetalles() {
        return repository.findAll();
    }

    public CarritoDetalle getCarritoDetalle(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El carrito detalle con id [" + id + "], no existe"));
    }

    public CarritoDetalle addCarritoDetalle(Long carritoId, CarritoDetalleDTO dto) {
        Carrito carrito = carritoRepository.findById(carritoId)
                .orElseThrow(() -> new IllegalArgumentException("Carrito no encontrado con id [" + carritoId + "]"));

        Producto producto = productoRepository.findById(dto.getProductoId())
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con id [" + dto.getProductoId() + "]"));

        CarritoDetalle detalle = new CarritoDetalle();
        detalle.setProducto(producto);
        detalle.setCantidad(dto.getCantidad());
        detalle.setCarrito(carrito);
        detalle.calcularSubtotal();

        return repository.save(detalle);
    }

    public CarritoDetalle updateCarritoDetalle(Long detalleId, CarritoDetalleDTO dto) {
        CarritoDetalle detalle = repository.findById(detalleId)
                .orElseThrow(() -> new IllegalArgumentException(
                    "El carrito detalle con id [" + detalleId + "] no existe"));

        
        if (dto.getProductoId() != null) {
            Producto producto = productoRepository.findById(dto.getProductoId())
                    .orElseThrow(() -> new IllegalArgumentException(
                        "Producto no encontrado con id [" + dto.getProductoId() + "]"));
            detalle.setProducto(producto);
        }

        if (dto.getCantidad() != null) {
            detalle.setCantidad(dto.getCantidad());
        }

        detalle.calcularSubtotal();

        return repository.save(detalle);
    }

    
}
