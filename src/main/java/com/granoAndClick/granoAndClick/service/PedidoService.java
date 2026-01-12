package com.granoAndClick.granoAndClick.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.granoAndClick.granoAndClick.model.Pedido;
import com.granoAndClick.granoAndClick.repository.PedidoRepository;

@Service
public class PedidoService {

	private final PedidoRepository repository;

	@Autowired
	public PedidoService(PedidoRepository repository) {
		this.repository = repository;
	}

	public List<Pedido> obtenerTodos() {
		return repository.findAll();
	}

	public Pedido obtenerPorId(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("El pedido con el id [" + id + "] no existe"));
	}

	public List<Pedido> obtenerPorUsuario(Long usuarioId) {
		return repository.findByUsuarioId(usuarioId);
	}

	public Pedido guardarPedido(Pedido pedido) {
		return repository.save(pedido);
	}

	public void borrarPedido(Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
		} else {
			throw new IllegalArgumentException("No se puede eliminar: el pedido con id [" + id + "] no existe");
		}
	}

	public Pedido actualizarEstado(Long id, String nuevoEstado) {
		Pedido pedido = repository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("No se encontró el pedido para actualizar"));
		
		if (nuevoEstado != null) {
			pedido.setEstado(nuevoEstado);
		}
		
		return repository.save(pedido);
	}

}// class PedidoService