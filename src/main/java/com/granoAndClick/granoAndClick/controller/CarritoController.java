package com.granoAndClick.granoAndClick.controller;

import com.granoAndClick.granoAndClick.dto.CarritoDTO;
import com.granoAndClick.granoAndClick.dto.CarritoResponseDTO;
import com.granoAndClick.granoAndClick.model.Carrito;
import com.granoAndClick.granoAndClick.service.CarritoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carrito")
public class CarritoController {

    private final CarritoService service;

    @Autowired
    public CarritoController(CarritoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Carrito> getAllCarritos() {
        return service.getAllCarritos();
    }
    @GetMapping("{carritoId}")
    public Carrito getCarrito(@PathVariable ("carritoId") Long id) {
    	return service.getCarrito(id);
    }

    @PostMapping
    public CarritoResponseDTO createCarrito(@RequestBody CarritoDTO dto) { 
    	return service.addCarrito(dto);
    }

    @DeleteMapping("/{carritoId}")
    public Carrito deleteCarrito(@PathVariable Long id) {
        return service.deleteCarrito(id);
    }
    @PutMapping("/{carritoId}")
    public CarritoResponseDTO updateCarrito(@PathVariable("carritoId") Long id, @RequestBody CarritoDTO dto) {
        return service.updateCarrito(id, dto);
    }


}
