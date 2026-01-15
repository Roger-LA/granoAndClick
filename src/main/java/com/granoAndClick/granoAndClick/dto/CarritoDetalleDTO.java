package com.granoAndClick.granoAndClick.dto;

public class CarritoDetalleDTO {
    private Long productoId;
    private Integer cantidad;
    private Long carritoId;
    
    public CarritoDetalleDTO() {
    
    }
	public Long getProductoId() {
		return productoId;
	}
	public void setProductoId(Long productoId) {
		this.productoId = productoId;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public Long getCarritoId() {
		return carritoId;
	}
	public void setCarritoId(Long carritoId) {
		this.carritoId = carritoId;
	}



    
}
