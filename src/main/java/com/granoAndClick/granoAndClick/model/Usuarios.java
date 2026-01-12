package com.granoAndClick.granoAndClick.model;

import jakarta.persistence.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	private String correo;// varchar 100
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
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="tipo_usuario_id", referencedColumnName="tipo_usuario_id") 
	private TiposUsuarios tiposUsuario;
	
	@CreationTimestamp
	@Column(length = 10,nullable=true,updatable = false)
	private LocalDateTime fecha_registro;// TIMESTAMP
	

	public Usuarios(String nombres, String apellidos, String correo, String telefono,
			LocalDate fecha_nacimiento, String calle_numero, String municipio, String colonia, String codigo_postal,
			String contrasena, Long tipo_usuario_id, LocalDateTime fecha_registro, TiposUsuarios tiposUsuario) {
		super();
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.correo = correo;
		this.telefono = telefono;
		this.fecha_nacimiento = fecha_nacimiento;
		this.calle_numero = calle_numero;
		this.municipio = municipio;
		this.colonia = colonia;
		this.codigo_postal = codigo_postal;
		this.contrasena = contrasena;
		this.tiposUsuario = tiposUsuario;
		this.fecha_registro = fecha_registro;
	}
	
	public Usuarios() {
		
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

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
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

	public TiposUsuarios getTiposUsuario() {
		return tiposUsuario;
	}

	public void setTipoUsuario(TiposUsuarios tiposUsuario) {
		this.tiposUsuario = tiposUsuario;
	}

	public LocalDateTime getFecha_registro() {
		return fecha_registro;
	}

	public void setFecha_registro(LocalDateTime fecha_registro) {
		this.fecha_registro = fecha_registro;
	}

	public Long getUsuario_id() {
		return usuario_id;
	}

	public void setUsuario_id(Long usuario_id) {
		this.usuario_id = usuario_id;
	}

	public TiposUsuarios getTiposUuario() {
		return tiposUsuario;
	}

	@Override
	public String toString() {
		return "Usuarios [nombres=" + nombres + ", apellidos=" + apellidos + ", correo="
				+ correo + ", telefono=" + telefono + ", fecha_nacimiento=" + fecha_nacimiento
				+ ", calle_numero=" + calle_numero + ", municipio=" + municipio + ", colonia=" + colonia
				+ ", codigo_postal=" + codigo_postal + ", contrasena=" + contrasena + ", tipo_usuario_id="
				+ tiposUsuario + ", fecha_registro=" + fecha_registro + "]";
	}
	
	
}
