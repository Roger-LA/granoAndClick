package com.granoAndClick.granoAndClick.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.granoAndClick.granoAndClick.model.Contactos;
import com.granoAndClick.granoAndClick.service.ContactosService;

@RestController
@RequestMapping("/api/contactos")
@CrossOrigin(origins = "*")
public class ContactosController {

    @Autowired
    private ContactosService contactosService;

    @PostMapping
    public Contactos crearContacto(@RequestBody Contactos contacto) {
        return contactosService.guardarContacto(contacto);
    }

    @GetMapping
    public List<Contactos> obtenerContactos() {
        return contactosService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Contactos obtenerContactoPorId(@PathVariable Long id) {
        return contactosService.obtenerPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminarContacto(@PathVariable Long id) {
        contactosService.eliminarContacto(id);
    }
}
