package com.granoAndClick.granoAndClick.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "pedidos")
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pedido_id", unique = true, nullable = false)
	private Long pedidoId;

	@Column(name = "usuario_id", unique = true, nullable = false)
	private Long usuarioId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_pedido", unique = true, nullable = false)
	private Date fechaPedido;

	private String estado;

	@Column(name = "costo_envio", unique = true, nullable = false)
	private BigDecimal costoEnvio;

	private BigDecimal total;

	public Pedido() {
	}

	public Pedido(Long usuarioId, Date fechaPedido, String estado, BigDecimal costoEnvio, BigDecimal total) {
		this.usuarioId = usuarioId;
		this.fechaPedido = fechaPedido;
		this.estado = estado;
		this.costoEnvio = costoEnvio;
		this.total = total;
	}

	public Long getPedidoId() {
		return pedidoId;
	}

	public void setPedidoId(Long pedidoId) {
		this.pedidoId = pedidoId;
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public Date getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public BigDecimal getCostoEnvio() {
		return costoEnvio;
	}

	public void setCostoEnvio(BigDecimal costoEnvio) {
		this.costoEnvio = costoEnvio;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + pedidoId + ", usuario=" + usuarioId + ", total=" + total + "]";
	}
}
