package com.granoAndClick.granoAndClick.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "carrito_detalle")
public class CarritoDetalle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "detalle_id", unique = true, nullable = false)
	private Long detalleId;

	@Column(name = "carrito_id", unique = true, nullable = false)
	private Long carritoId;

	 @ManyToOne // Relación con Producto
	    @JoinColumn(name = "producto_id", nullable = false)
	    private Producto producto; // Cambiado de Long a Producto

	private Integer cantidad;

	@Column(name = "subtotal", unique = true, nullable = false)
	private BigDecimal subtotal;

	public CarritoDetalle() {
	}

	public Long getDetalleId() {
		return detalleId;
	}

	public void setDetalleId(Long detalleId) {
		this.detalleId = detalleId;
	}

	public Long getCarritoId() {
		return carritoId;
	}

	public void setCarritoId(Long carritoId) {
		this.carritoId = carritoId;
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

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
}
