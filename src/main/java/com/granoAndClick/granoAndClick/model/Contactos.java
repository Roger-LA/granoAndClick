package com.granoAndClick.granoAndClick.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "contactos")
public class Contactos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, name = "contactos_id")
    private Long contactoId;

    @Column(length = 70)
    private String nombre;

    @Column(length = 70)
    private String correo;

    @Column(length = 10)
    private String telefono;

    @Column(unique = true, nullable = false)
    private String mensaje;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estado estado;

    @Column(length = 300)
    private String nota;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fecha;

    public enum Estado {
        leido,
        resuelto,
        en_proceso
    }

    
    public Contactos(String nombre, String correo, String telefono, String mensaje) {
		this.nombre = nombre;
		this.correo = correo;
		this.telefono = telefono;
		this.mensaje = mensaje;
		this.nota = "";
        this.estado = Estado.leido;
        this.fecha = LocalDateTime.now();
	}

	public Contactos() {
        this.estado = Estado.leido;
        this.fecha = LocalDateTime.now();
    }

    public Long getcontactoId() {
        return contactoId;
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

}
