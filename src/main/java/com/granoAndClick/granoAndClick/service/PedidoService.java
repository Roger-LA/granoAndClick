package com.granoAndClick.granoAndClick.service;

import com.granoAndClick.granoAndClick.dto.PedidoDTO;
import com.granoAndClick.granoAndClick.dto.PedidoDetalleDTO;
import com.granoAndClick.granoAndClick.dto.PedidoResponseDTO;
import com.granoAndClick.granoAndClick.model.Pedido;
import com.granoAndClick.granoAndClick.model.PedidoDetalle;
import com.granoAndClick.granoAndClick.model.Producto;
import com.granoAndClick.granoAndClick.model.Usuarios;
import com.granoAndClick.granoAndClick.repository.PedidoRepository;
import com.granoAndClick.granoAndClick.repository.ProductoRepository;
import com.granoAndClick.granoAndClick.repository.UsuariosRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private UsuariosRepository usuariosRepository;

	@Autowired
	private ProductoRepository productoRepository;

	@Transactional
	public PedidoResponseDTO addPedido(PedidoDTO dto) {
		Usuarios usuario = usuariosRepository.findById(dto.getUsuarioId())
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + dto.getUsuarioId()));

		Pedido pedido = new Pedido();
		pedido.setUsuario(usuario);
		pedido.setFechaPedido(new Date());
		pedido.setCostoEnvio(dto.getCostoEnvio());

		List<PedidoDetalle> detalles = new ArrayList<>();
		if (dto.getDetalles() != null) {
			for (PedidoDetalleDTO detDto : dto.getDetalles()) {
				Producto producto = productoRepository.findById(detDto.getProductoId()).orElseThrow(
						() -> new RuntimeException("Producto no encontrado ID: " + detDto.getProductoId()));

				PedidoDetalle detalle = new PedidoDetalle();
				detalle.setPedido(pedido);
				detalle.setProducto(producto);
				detalle.setCantidad(detDto.getCantidad());
				detalle.setPrecioUnitario(detDto.getPrecioUnitario());
				detalles.add(detalle);
			}
		}
		pedido.setDetalles(detalles);

		pedido.calcularTotal();

		Pedido pedidoGuardado = pedidoRepository.save(pedido);

		return convertirAResponseDTO(pedidoGuardado);
	}

	@Transactional
	public PedidoResponseDTO updatePedido(Long id, PedidoDTO dto) {
		Pedido pedidoExistente = pedidoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

		pedidoExistente.setCostoEnvio(dto.getCostoEnvio());

		pedidoExistente.calcularTotal();

		Pedido actualizado = pedidoRepository.save(pedidoExistente);
		return convertirAResponseDTO(actualizado);
	}

	public List<Pedido> getTodos() {
		return pedidoRepository.findAll();
	}

	public Pedido getbyId(Long id) {
		return pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
	}

	@Transactional
	public void deletePedido(Long id) {
		pedidoRepository.deleteById(id);
	}

	private PedidoResponseDTO convertirAResponseDTO(Pedido pedido) {
		PedidoResponseDTO res = new PedidoResponseDTO();
		res.setPedidoId(pedido.getPedidoId());
		res.setUsuarioId(pedido.getUsuario().getUsuarioId());
		res.setFechaPedido(pedido.getFechaPedido());
		res.setEstado(pedido.getEstado());
		res.setCostoEnvio(pedido.getCostoEnvio());
		res.setTotal(pedido.getTotal());

		List<PedidoDetalleDTO> detallesDTO = pedido.getDetalles().stream().map(d -> {
			PedidoDetalleDTO detDto = new PedidoDetalleDTO();
			detDto.setPedidoId(pedido.getPedidoId());
			detDto.setProductoId(d.getProducto().getId());
			detDto.setNombreProducto(d.getProducto().getNombre());
			detDto.setCantidad(d.getCantidad());
			detDto.setPrecioUnitario(d.getPrecioUnitario());
			detDto.setSubtotal(d.getSubtotal());
			return detDto;
		}).collect(Collectors.toList());

		res.setDetalles(detallesDTO);
		return res;
	}
}