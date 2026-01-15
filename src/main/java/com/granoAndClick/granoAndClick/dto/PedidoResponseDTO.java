package com.granoAndClick.granoAndClick.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class PedidoResponseDTO {
    private Long pedidoId;
    private Long usuarioId;
    private Date fechaPedido;
    private String estado;
    private BigDecimal costoEnvio;
    private BigDecimal total;
    private List<PedidoDetalleDTO> detalles;
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
	public List<PedidoDetalleDTO> getDetalles() {
		return detalles;
	}
	public void setDetalles(List<PedidoDetalleDTO> detalles) {
		this.detalles = detalles;
	}

}