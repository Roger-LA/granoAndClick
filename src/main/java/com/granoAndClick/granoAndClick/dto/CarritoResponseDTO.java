package com.granoAndClick.granoAndClick.dto;

import java.math.BigDecimal;
import java.util.Set;

public class CarritoResponseDTO {
    private Long carritoId;
	private Long usuarioId;
    private BigDecimal costoEnvio;
    private Set<CarritoDetalleDTO> detalles;
    private BigDecimal total;
	public CarritoResponseDTO(Long carritoId, Long usuarioId, BigDecimal costoEnvio, Set<CarritoDetalleDTO> detalles,
			BigDecimal total) {
		super();
		this.carritoId = carritoId;
		this.usuarioId = usuarioId;
		this.costoEnvio = costoEnvio;
		this.detalles = detalles;
		this.total = total;
	}
	public Long getCarritoId() {
		return carritoId;
	}
	public void setCarritoId(Long carritoId) {
		this.carritoId = carritoId;
	}
	public Long getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}
	public BigDecimal getCostoEnvio() {
		return costoEnvio;
	}
	public void setCostoEnvio(BigDecimal costoEnvio) {
		this.costoEnvio = costoEnvio;
	}
	public Set<CarritoDetalleDTO> getDetalles() {
		return detalles;
	}
	public void setDetalles(Set<CarritoDetalleDTO> detalles) {
		this.detalles = detalles;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}

    
}
