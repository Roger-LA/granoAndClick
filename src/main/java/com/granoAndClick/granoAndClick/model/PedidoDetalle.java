package com.granoAndClick.granoAndClick.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "pedido_detalle")
public class PedidoDetalle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "detalle_id", unique = true, nullable = false)
	private Long detalleId;

	@ManyToOne
	@JoinColumn(name = "pedido_id", nullable = false, unique = false)
	private Pedido pedido;

	@ManyToOne
	@JoinColumn(name = "producto_id", nullable = false, unique = false)
	private Producto producto;

	@Column(name = "cantidad", nullable = false)
	private Integer cantidad;

	@Column(name = "precio_unitario", nullable = false, precision = 9, scale = 2)
	private BigDecimal precioUnitario;

	public PedidoDetalle() {
	}

	public BigDecimal getSubtotal() {
		if (precioUnitario != null && cantidad != null) {
			return precioUnitario.multiply(new BigDecimal(cantidad));
		}
		return BigDecimal.ZERO;
	}

	public Long getDetalleId() {
		return detalleId;
	}

	public void setDetalleId(Long detalleId) {
		this.detalleId = detalleId;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
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

	public BigDecimal getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(BigDecimal precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
}