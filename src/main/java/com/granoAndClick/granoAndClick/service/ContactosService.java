package com.granoAndClick.granoAndClick.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.granoAndClick.granoAndClick.model.Contactos;
import com.granoAndClick.granoAndClick.repository.ContactosRepository;

@Service
public class ContactosService {

    @Autowired
    private ContactosRepository repository;

    public Contactos guardarContacto(Contactos contacto) {
        if (repository.findByMensaje(contacto.getMensaje()).isPresent()) {
            throw new IllegalStateException("ese contacto ya existe");
        }
        return repository.save(contacto);
    }

    public List<Contactos> obtenerTodos() {
        return repository.findAll();
    }

    public Contactos obtenerPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void eliminarContacto(Long id) {
        repository.deleteById(id);
    }
}
