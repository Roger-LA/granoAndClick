package com.granoAndClick.granoAndClick.service;

import com.granoAndClick.granoAndClick.model.Carrito;
import com.granoAndClick.granoAndClick.repository.CarritoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarritoService {

    private final CarritoRepository repository;

    public CarritoService(CarritoRepository repository) {
        this.repository = repository;
    }

    public List<Carrito> getAllCarritos() {
        return repository.findAll();
    }

    public Carrito saveCarrito(Carrito carrito) {
        return repository.save(carrito);
    }

    public void deleteCarrito(Long id) {
        repository.deleteById(id);
    }
}
