package com.granoAndClick.granoAndClick.model;

import java.math.BigDecimal;

import java.time.LocalDateTime;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import com.granoAndClick.granoAndClick.dto.CarritoDetalleDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "carrito")
public class Carrito {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "carrito_id", unique = true, nullable = false)
	private Long carritoId;

	@Column(name = "costo_envio", nullable = false, precision = 5, scale = 2)
	private BigDecimal costoEnvio;

	@Column(nullable = false, precision = 9, scale = 2)
	private BigDecimal total;

	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	private LocalDateTime fechaAgregado;

	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false, referencedColumnName = "usuario_id")
	private Usuarios usuarios;

	@OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<CarritoDetalle> detalles;

	public Carrito(BigDecimal costoEnvio, Usuarios usuarios, Set<CarritoDetalle> detalles) {
		this.costoEnvio = costoEnvio;
		this.calcularTotal();
		this.usuarios = usuarios;
		this.detalles = detalles;
		this.fechaAgregado = LocalDateTime.now();
	}

	public Carrito() {
	}

	public Long getCarritoId() {
		return carritoId;
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

	public LocalDateTime getFechaAgregado() {
		return fechaAgregado;
	}

	public void setFechaAgregado(LocalDateTime fechaAgregado) {
		this.fechaAgregado = LocalDateTime.now();

	}

	public Usuarios getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

	public Set<CarritoDetalle> getDetalles() {
		return detalles;
	}

	public void setDetalles(Set<CarritoDetalle> detalles) {
		this.detalles = detalles;
	}

	public void calcularTotal() {
		BigDecimal subtotalCarrito = BigDecimal.ZERO;

		if (detalles != null) {
			for (CarritoDetalle detalle : detalles) {
				if (detalle.getSubtotal() != null) {
					subtotalCarrito = subtotalCarrito.add(detalle.getSubtotal());
				}
			}
		}

		if (costoEnvio != null) {
			this.total = subtotalCarrito.add(costoEnvio);
		} else {
			this.total = subtotalCarrito;
		}
	}

}
