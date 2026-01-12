package com.granoAndClick.granoAndClick.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.granoAndClick.granoAndClick.model.PedidoDetalle;
import com.granoAndClick.granoAndClick.service.PedidoDetalleService;

@RestController
@RequestMapping(path = "/api/detalles-pedidos")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
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
    public PedidoDetalle addDetalle(@RequestBody PedidoDetalle detalle) {
        return detalleService.addDetalle(detalle);
    }
    
    @PutMapping("/{id}")
    public PedidoDetalle updateDetalle(@PathVariable("id") Long id, @RequestBody PedidoDetalle detalle) {
        return detalleService.updateDetalle(id, detalle);
    }

    @DeleteMapping("/{id}")
    public void deleteDetalle(@PathVariable("id") Long id) {
        detalleService.deleteDetalle(id);
    }
}

