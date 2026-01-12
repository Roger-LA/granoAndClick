package com.granoAndClick.granoAndClick.model;

import jakarta.persistence.*;

@Entity
@Table(name = "carrito")
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreProducto;
    private Double precio;
    private Integer cantidad;
    private Double total;

    public Carrito() {
    }

    public Carrito(Long id, String nombreProducto, Double precio, Integer cantidad, Double total) {
        this.id = id;
        this.nombreProducto = nombreProducto;
        this.precio = precio;
        this.cantidad = cantidad;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Carrito [id=" + id + ", nombreProducto=" + nombreProducto +
               ", precio=" + precio + ", cantidad=" + cantidad +
               ", total=" + total + "]";
    }
}
