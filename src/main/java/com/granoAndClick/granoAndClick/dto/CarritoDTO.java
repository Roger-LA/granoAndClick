package com.granoAndClick.granoAndClick.dto;

import java.math.BigDecimal;
import java.util.Set;

public class CarritoDTO {
    private Long usuarioId;
    private BigDecimal costoEnvio;
    
    
    private Set<CarritoDetalleDTO> detalles;
    public CarritoDTO() {}

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
}
