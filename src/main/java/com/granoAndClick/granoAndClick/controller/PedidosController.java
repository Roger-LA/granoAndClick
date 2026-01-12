package com.granoAndClick.granoAndClick.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.granoAndClick.granoAndClick.model.Pedido;
import com.granoAndClick.granoAndClick.service.PedidoService;


@RestController
@RequestMapping(path = "/api/pedidos")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class PedidosController {

    private final PedidoService pedidoService;

    @Autowired
    public PedidosController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public List<Pedido> getPedidos() {
        return pedidoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Pedido getPedido(@PathVariable("id") Long id) {
        return pedidoService.obtenerPorId(id); 
    }

    @PostMapping
    public Pedido createPedido(@RequestBody Pedido pedido) {
        return pedidoService.guardarPedido(pedido);
    }
    
    @PutMapping("/{id}")
    public Pedido updatePedido(@PathVariable("id") Long id, @RequestBody Pedido pedido) {
        return pedidoService.actualizarPedido(id, pedido);
    }

    @DeleteMapping("/{id}")
    public void deletePedido(@PathVariable("id") Long id) {
        pedidoService.borrarPedido(id);
    }
}
