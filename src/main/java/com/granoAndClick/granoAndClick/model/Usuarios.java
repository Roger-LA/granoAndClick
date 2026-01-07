package com.granoAndClick.granoAndClick.model;

import jakarta.persistence.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="usuarios")
public class Usuarios {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="usuario_id", unique=true, nullable=false)
	private Long usuario_id;
	@Column(length = 70, nullable=false)
	private String nombres;// varchar 70
	@Column(length = 70, nullable=false)
	private String apellidos;// varchar 70
	@Column(length = 100, nullable=false)
	private String correo_electronico;// varchar 100
	@Column(length = 10, nullable=false)
	private String telefono;// varchar 10
	@Column(length = 10,nullable=false)	
	private LocalDate fecha_nacimiento;// DATE
	@Column(length = 70,nullable=false)
	private String calle_numero;// varchar 70
	@Column(length = 100, nullable=false)
	private String municipio;// varchar 100
	@Column(length = 100, nullable=false)
	private String colonia;// varchar 100
	@Column(length = 5, nullable=false)
	private String codigo_postal;// varchar 5
	@Column(length = 255,nullable=false)
	private String contrasena;// varchar 255
	@Column(length = 10,nullable=false)
	private Long tipo_usuario_id;// INT

	@CreationTimestamp
	@Column(length = 10,nullable=true,updatable = false)
	private LocalDateTime fecha_registro;// TIMESTAMP
	

	public Usuarios(String nombres, String apellidos, String correo_electronico, String telefono,
			LocalDate fecha_nacimiento, String calle_numero, String municipio, String colonia, String codigo_postal,
			String contrasena, Long tipo_usuario_id, LocalDateTime fecha_registro) {
		super();
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.correo_electronico = correo_electronico;
		this.telefono = telefono;
		this.fecha_nacimiento = fecha_nacimiento;
		this.calle_numero = calle_numero;
		this.municipio = municipio;
		this.colonia = colonia;
		this.codigo_postal = codigo_postal;
		this.contrasena = contrasena;
		this.tipo_usuario_id = tipo_usuario_id;
		this.fecha_registro = fecha_registro;
	}
	
	public Usuarios() {
		super();
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCorreo_electronico() {
		return correo_electronico;
	}

	public void setCorreo_electronico(String correo_electronico) {
		this.correo_electronico = correo_electronico;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public LocalDate getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public String getCalle_numero() {
		return calle_numero;
	}

	public void setCalle_numero(String calle_numero) {
		this.calle_numero = calle_numero;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getColonia() {
		return colonia;
	}

	public void setColonia(String colonia) {
		this.colonia = colonia;
	}

	public String getCodigo_postal() {
		return codigo_postal;
	}

	public void setCodigo_postal(String codigo_postal) {
		this.codigo_postal = codigo_postal;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public Long getTipo_usuario_id() {
		return tipo_usuario_id;
	}

	public void setTipo_usuario_id(Long tipo_usuario_id) {
		this.tipo_usuario_id = tipo_usuario_id;
	}

	public LocalDateTime getFecha_registro() {
		return fecha_registro;
	}

	public void setFecha_registro(LocalDateTime fecha_registro) {
		this.fecha_registro = fecha_registro;
	}

	@Override
	public String toString() {
		return "Usuarios [nombres=" + nombres + ", apellidos=" + apellidos + ", correo_electronico="
				+ correo_electronico + ", telefono=" + telefono + ", fecha_nacimiento=" + fecha_nacimiento
				+ ", calle_numero=" + calle_numero + ", municipio=" + municipio + ", colonia=" + colonia
				+ ", codigo_postal=" + codigo_postal + ", contrasena=" + contrasena + ", tipo_usuario_id="
				+ tipo_usuario_id + ", fecha_registro=" + fecha_registro + "]";
	}
	
	
}
