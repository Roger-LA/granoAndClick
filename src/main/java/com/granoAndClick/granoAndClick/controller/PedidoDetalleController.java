package com.granoAndClick.granoAndClick.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.granoAndClick.granoAndClick.dto.PedidoDetalleDTO;
import com.granoAndClick.granoAndClick.model.PedidoDetalle;
import com.granoAndClick.granoAndClick.service.PedidoDetalleService;

@RestController
@RequestMapping(path = "/api/pedidosDetalle")
public class PedidoDetalleController {

    private final PedidoDetalleService detalleService;

    @Autowired
    public PedidoDetalleController(PedidoDetalleService detalleService) {
        this.detalleService = detalleService;
    }

    @GetMapping
    public List<PedidoDetalle> getAll() {
        return detalleService.getAllDetalles();
    }
 
    @GetMapping("/pedido/{pedidoId}")
    public List<PedidoDetalle> getByPedido(@PathVariable("pedidoId") Long pedidoId) {
        return detalleService.getDetallesByPedido(pedidoId);
    }

    @PostMapping
    public PedidoDetalle addDetalle(@RequestBody PedidoDetalleDTO detalleDto) {
        return detalleService.addDetalle(detalleDto);
    }
    
    @PutMapping("/{id}")
    public PedidoDetalle updateDetalle(@PathVariable("id") Long id, @RequestBody PedidoDetalleDTO detalleDto) {
        return detalleService.updateDetalle(id, detalleDto);
    }

    @DeleteMapping("/{id}")
    public void deleteDetalle(@PathVariable("id") Long id) {
        detalleService.deleteDetalle(id);
    }
}