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
	@JoinColumn(name = "pedido_id", nullable = false)
	private Pedido pedido;

	@ManyToOne
	@JoinColumn(name = "producto_id", nullable = false)
	private Producto producto;

	private Integer cantidad;

	@Column(name = "precio_unitario", nullable = false)
	private BigDecimal precioUnitario;

	public PedidoDetalle() {
	}

	public Long getDetalleId() {
		return detalleId;
	}

	public void setDetalleId(Long detalleId) {
		this.detalleId = detalleId;
	}

	public Pedido getPedidoId() {
		return pedido;
	}

	public void setPedidoId(Pedido pedidoId) {
		this.pedido = pedidoId;
	}

	public Producto getProductoId() {
		return producto;
	}

	public void setProductoId(Producto productoId) {
		this.producto = productoId;
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

	@Override
	public String toString() {
		return "PedidoDetalle [detalleId=" + detalleId + ", pedidoId=" + pedido + ", productoId=" + producto
				+ ", cantidad=" + cantidad + ", precioUnitario=" + precioUnitario + "]";
	}
	
}
