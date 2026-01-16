package com.granoAndClick.granoAndClick.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.granoAndClick.granoAndClick.model.Contactos;
import com.granoAndClick.granoAndClick.service.ContactosService;

@RestController
@RequestMapping("/api/contactos")
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

    @GetMapping("/{contactoId}")
    public Contactos getContactoPorId(@PathVariable ("contactoId") Long id) {
        return contactosService.obtenerPorId(id);
    }

    @DeleteMapping("/{contactoId}")
    public void eliminarContacto(@PathVariable ("contactoId") Long id) {
        contactosService.eliminarContacto(id);
    }
}
