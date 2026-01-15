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

	@Column(name = "estado", nullable = false)
	private String estado;

	@Column(name = "costo_envio", nullable = false, precision = 5, scale = 2)
	private BigDecimal costoEnvio;

	@Column(name = "total", nullable = false, precision = 9, scale = 2)
	private BigDecimal total;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PedidoDetalle> detalles;

	public Pedido() {
	}

	public void calcularTotal() {
		BigDecimal subtotalPedido = BigDecimal.ZERO;

		if (detalles != null) {
			for (PedidoDetalle detalle : detalles) {
				subtotalPedido = subtotalPedido.add(detalle.getSubtotal());
			}
		}

		if (costoEnvio != null) {
			this.total = subtotalPedido.add(costoEnvio);
		} else {
			this.total = subtotalPedido;
		}
	}

	public Long getPedidoId() {
		return pedidoId;
	}

	public void setPedidoId(Long pedidoId) {
		this.pedidoId = pedidoId;
	}

	public Usuarios getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
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

	public List<PedidoDetalle> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<PedidoDetalle> detalles) {
		this.detalles = detalles;
	}
	
	
}