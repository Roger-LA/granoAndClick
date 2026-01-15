package com.granoAndClick.granoAndClick.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.granoAndClick.granoAndClick.repository.CarritoDetalleRepository;
import com.granoAndClick.granoAndClick.service.CarritoDetalleService;
import com.granoAndClick.granoAndClick.dto.CarritoDetalleDTO;
import com.granoAndClick.granoAndClick.model.CarritoDetalle;

@RestController
@RequestMapping("/api/carritoDetalle")
public class CarritoDetalleController {
	private final CarritoDetalleService service;

	@Autowired
	public CarritoDetalleController(CarritoDetalleService service ) {
		this.service = service;
	}
	
	@GetMapping
	public List<CarritoDetalle> getAllCarritoDetalles(){
		return service.getAllCarritoDetalles();
	}
	
	@GetMapping("/{carritoDetalleId}")
	public CarritoDetalle getcarritoDetalle(@PathVariable("carritoDetalleId") Long id) {
    	return service.getCarritoDetalle(id);

	}
	@PostMapping("/{carritoDetalleId}")
	public CarritoDetalle createCarritoDetalle(@PathVariable("carritoDetalleId") Long carritoId, @RequestBody CarritoDetalleDTO dto) { 
		return service.addCarritoDetalle(carritoId, dto);
	}
	
	@PutMapping("/{carritoDetalleId}")
	public CarritoDetalle updateCarritoDetalle(@PathVariable("carritoDetalleId") Long detalleId,
	                                           @RequestBody CarritoDetalleDTO dto) {
	    return service.updateCarritoDetalle(detalleId, dto);
	}

}
