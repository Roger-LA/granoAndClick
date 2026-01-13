package com.granoAndClick.granoAndClick.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import jakarta.persistence.Table;

@Entity
@Table(name="tipos_usuarios")
public class TiposUsuarios {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="tipo_usuario_id", unique = true, nullable = false)
	private Long id;
	@Column(length = 50, nullable = false)
	private String nombre;
	@Column(length = 150,nullable = false)
	private String descripcion;
	
	@OneToMany(mappedBy="tiposUsuario", cascade=CascadeType.ALL, orphanRemoval=true) 
	private List<Usuarios> usuarios = new ArrayList<>();
	
	public TiposUsuarios(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public TiposUsuarios() {
	}
	
	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "TiposUsuarios [nombre=" + nombre + ", descripcion=" + descripcion + "]";
	}

	
}
