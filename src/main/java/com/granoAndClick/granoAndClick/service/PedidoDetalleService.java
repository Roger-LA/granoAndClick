package com.granoAndClick.granoAndClick.service;

import com.granoAndClick.granoAndClick.dto.PedidoDetalleDTO;
import com.granoAndClick.granoAndClick.model.Pedido;
import com.granoAndClick.granoAndClick.model.PedidoDetalle;
import com.granoAndClick.granoAndClick.model.Producto;
import com.granoAndClick.granoAndClick.repository.PedidoDetalleRepository;
import com.granoAndClick.granoAndClick.repository.PedidoRepository;
import com.granoAndClick.granoAndClick.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PedidoDetalleService {

    @Autowired
    private PedidoDetalleRepository detalleRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public List<PedidoDetalle> getAllDetalles() {
        return detalleRepository.findAll();
    }

    public List<PedidoDetalle> getDetallesByPedido(Long pedidoId) {
        return detalleRepository.findByPedido_PedidoId(pedidoId);
    }

    @Transactional
    public PedidoDetalle addDetalle(PedidoDetalleDTO dto) {
		Pedido pedido = pedidoRepository
				.findById(dto.getPedidoId())
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        
        Producto producto = productoRepository.findById(dto.getProductoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        PedidoDetalle detalle = new PedidoDetalle();
        detalle.setPedido(pedido);
        detalle.setProducto(producto);
        detalle.setCantidad(dto.getCantidad());
        detalle.setPrecioUnitario(dto.getPrecioUnitario());

        PedidoDetalle guardado = detalleRepository.save(detalle);

        pedido.getDetalles().add(guardado);
        pedido.calcularTotal();
        pedidoRepository.save(pedido);

        return guardado;
    }

    @Transactional
    public PedidoDetalle updateDetalle(Long id, PedidoDetalleDTO dto) {
        PedidoDetalle detalleExistente = detalleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle no encontrado"));

        detalleExistente.setCantidad(dto.getCantidad());
        detalleExistente.setPrecioUnitario(dto.getPrecioUnitario());

        PedidoDetalle actualizado = detalleRepository.save(detalleExistente);

        Pedido pedido = actualizado.getPedido();
        pedido.calcularTotal();
        pedidoRepository.save(pedido);

        return actualizado;
    }

    @Transactional
    public void deleteDetalle(Long id) {
        PedidoDetalle detalle = detalleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle no encontrado"));
        
        Pedido pedido = detalle.getPedido();
        
        detalleRepository.delete(detalle);

        pedido.getDetalles().remove(detalle);
        pedido.calcularTotal();
        pedidoRepository.save(pedido);
    }
}