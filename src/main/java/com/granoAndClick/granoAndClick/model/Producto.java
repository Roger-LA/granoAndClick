package com.granoAndClick.granoAndClick.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="productos")
public class Producto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	@Column(unique = true, nullable = false, length = 10)
	private String subindice;
	@Column(nullable = false, length = 20)
	private String categoria;
	@Column(nullable = false, length = 35)
	private String nombre;
	@Column(nullable = false, length = 70)
	private String descripcion;
	@Column(nullable = false, length = 7)
	private Double precio;
	@Column(nullable = false, length = 255)
	private String imagen_url;
	@Column(name = "activo", nullable = false)
	private Boolean True;
	
	
	public Producto(String subindice, String categoria, String nombre, String descripcion, Double precio,
			String imagen_url, Integer activo, Boolean True) {
		this.subindice = subindice;
		this.categoria = categoria;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.imagen_url = imagen_url;
		this.True = True;
	}//constructorProductos
	
	public Producto(){
		
	}//constructorvacio

	public Long getId() {
		return id;
	}

	public String getSubindice() {
		return subindice;
	}

	public void setSubindice(String subindice) {
		this.subindice = subindice;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
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

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public String getImagen_url() {
		return imagen_url;
	}

	public void setImagen_url(String imagen_url) {
		this.imagen_url = imagen_url;
	}

	public Boolean  isActivo() {
		return True;
	}

	public void setActivo(Boolean True) {
		this.True = True;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", subindice=" + subindice + ", categoria=" + categoria + ", nombre=" + nombre
				+ ", descripcion=" + descripcion + ", precio=" + precio + ", imagen_url=" + imagen_url + ", True="
				+ True + "]";
	}//toString

	
}
