package com.granoAndClick.granoAndClick.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.granoAndClick.granoAndClick.dto.PedidoDTO;
import com.granoAndClick.granoAndClick.dto.PedidoResponseDTO;
import com.granoAndClick.granoAndClick.model.Pedido;
import com.granoAndClick.granoAndClick.service.PedidoService;

@RestController
@RequestMapping(path = "/api/pedidos")
public class PedidosController {

	private final PedidoService pedidoService;

	@Autowired
	public PedidosController(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}

	@GetMapping
	public List<Pedido> getPedidos() {
		return pedidoService.getTodos();
	}

	@GetMapping("/{id}")
	public Pedido getPedido(@PathVariable("id") Long id) {
		return pedidoService.getbyId(id);
	}

	@PostMapping
	public PedidoResponseDTO createPedido(@RequestBody PedidoDTO pedidoDto) {
		return pedidoService.addPedido(pedidoDto);
	}

	@PutMapping("/{id}")
	public PedidoResponseDTO updatePedido(@PathVariable("id") Long id, @RequestBody PedidoDTO pedidoDto) {
		return pedidoService.updatePedido(id, pedidoDto);
	}

	@DeleteMapping("/{id}")
	public void deletePedido(@PathVariable("id") Long id) {
		pedidoService.deletePedido(id);
	}
}
