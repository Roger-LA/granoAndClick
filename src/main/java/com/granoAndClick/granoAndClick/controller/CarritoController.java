package com.granoAndClick.granoAndClick.controller;

import com.granoAndClick.granoAndClick.model.Carrito;
import com.granoAndClick.granoAndClick.service.CarritoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carrito")
public class CarritoController {

    private final CarritoService service;

    public CarritoController(CarritoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Carrito> getAllCarritos() {
        return service.getAllCarritos();
    }

    @PostMapping
    public Carrito createCarrito(@RequestBody Carrito carrito) {
        return service.saveCarrito(carrito);
    }

    @DeleteMapping("/{id}")
    public void deleteCarrito(@PathVariable Long id) {
        service.deleteCarrito(id);
    }
}
