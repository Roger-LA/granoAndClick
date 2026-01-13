package com.granoAndClick.granoAndClick.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "carrito")
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "carrito_id", unique = true, nullable = false)
    private Long id;
    @Column(name = "usuario_id")
    private Integer usuarioId;
    @Column(name = "costo_envio", precision = 5, scale = 2)
    private java.math.BigDecimal costoEnvio;
    @Column(name = "total", precision = 9, scale = 2)
    private BigDecimal total;
    @Column(name = "fecha_agregado")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date fechaAgregado;
    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL)
    private List<CarritoDetalle> carritoDetalles;
    //@ManyToOne
    //@JoinColumn(name = "usario_id")
    //private Usuarios usuario;

    public Carrito() {
    }

    public Carrito(Long id, BigDecimal costoEnvio, BigDecimal total, Date fechaAgregado) {
    	this.id = id;
		this.costoEnvio = costoEnvio;
		this.total = total;
		this.fechaAgregado = fechaAgregado;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public java.math.BigDecimal getCostoEnvio() {
		return costoEnvio;
	}

	public void setCostoEnvio(java.math.BigDecimal costoEnvio) {
		this.costoEnvio = costoEnvio;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public java.util.Date getFechaAgregado() {
		return fechaAgregado;
	}

	public void setFechaAgregado(java.util.Date fechaAgregado) {
		this.fechaAgregado = fechaAgregado;
	}

	@Override
	public String toString() {
		return "Carrito [id=" + id + ", costoEnvio=" + costoEnvio + ", total=" + total + ", fechaAgregado="
				+ fechaAgregado + "]";
	}

}
