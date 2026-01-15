package com.granoAndClick.granoAndClick.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "carrito_detalle")
public class CarritoDetalle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "carrito_detalle_id", unique = true, nullable = false)
	private Long carritoDetalleId;

	@ManyToOne
	@JoinColumn(name = "producto_id", nullable = false)
	private Producto producto;

	@Column(nullable = false)
	private Integer cantidad;

	@Column(precision = 7, scale = 2)
	private BigDecimal subtotal;

	@ManyToOne
	@JoinColumn(name = "carrito_id", nullable = false, referencedColumnName = "carrito_id")
	private Carrito carrito;

	
	
	public CarritoDetalle() {
	}

	public CarritoDetalle(Producto producto, Integer cantidad, Carrito carrito) {
		this.producto = producto;
		this.cantidad = cantidad;
		this.carrito = carrito;
		this.calcularSubtotal();
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public Carrito getCarrito() {
		return carrito;
	}

	public void setCarrito(Carrito carrito) {
		this.carrito = carrito;
	}

	public Long getCarritoDetalleId() {
		return carritoDetalleId;
	}
	public void calcularSubtotal() {
	    if (producto != null && cantidad != null) {
	        this.subtotal = BigDecimal.valueOf(cantidad * producto.getPrecio());
	    }
	}


	@Override
	public String toString() {
		return "CarritoDetalle [detalleId=" + carritoDetalleId + ", producto=" + producto + ", cantidad=" + cantidad
				+ ", subtotal=" + subtotal + ", carrito=" + carrito + "]";
	}

	
	
}
