package com.granoAndClick.granoAndClick.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pedido_id")
	private Long pedidoId;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuarios usuario;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_pedido", nullable = false)
	private Date fechaPedido;

	private String estado;

	@Column(name = "costo_envio", nullable = false)
	private BigDecimal costoEnvio;

	private BigDecimal total;
	
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<PedidoDetalle> detalles;

	public Pedido() {
	}

	public Pedido(Usuarios usuarioId, Date fechaPedido, String estado, BigDecimal costoEnvio, BigDecimal total) {
		this.usuario = usuarioId;
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

	public Usuarios getUsuarioId() {
		return usuario;
	}

	public void setUsuarioId(Usuarios usuarioId) {
		this.usuario = usuarioId;
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
		return "Pedido [id=" + pedidoId + ", usuario=" + usuario + ", total=" + total + "]";
	}
}
