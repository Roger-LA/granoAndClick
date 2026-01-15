package com.granoAndClick.granoAndClick.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "usuarios")
public class Usuarios {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="usuario_id", unique=true, nullable=false)
	private Long usuarioId;

	@Column(length = 70, nullable=false)
	private String nombres;// varchar 70
	@Column(length = 70, nullable=false)
	private String apellidos;// varchar 70
	@Column(length = 100, nullable=false)
	private String correo;// varchar 100
	@Column(length = 10, nullable=false)
	private String telefono;// varchar 10
	@Column(length = 10,nullable=false)	
	private LocalDate fechaNacimiento;// DATE
	@Column(length = 70,nullable=false)
	private String calleNumero;// varchar 70
	@Column(length = 100, nullable=false)
	private String municipio;// varchar 100
	@Column(length = 100, nullable=false)
	private String colonia;// varchar 100
	@Column(length = 5, nullable=false)
	private String codigoPostal;// varchar 5
	@Column(length = 255,nullable=false)
	private String contrasena;// varchar 255
	@Column(length = 255,nullable=true)
	private String subindice;
	@ManyToOne()
	@JoinColumn(name = "tipo_usuario_id", referencedColumnName = "tipo_usuario_id")
	private TiposUsuarios tiposUsuario;
	
	@CreationTimestamp
	@Column(length = 10,nullable=true,updatable = false)
	private LocalDateTime fechaRegistro;// TIMESTAMP
	
   @OneToMany(mappedBy = "usuarios")
    private Set<Carrito> carrito;
	
	public Usuarios(String nombres, String apellidos, String correo, String telefono,
			LocalDate fechaNacimiento, String calleNumero, String municipio, String colonia, String codigoPostal,
			String contrasena, TiposUsuarios tiposUsuario, LocalDateTime fechaRegistro) {
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.correo = correo;
		this.telefono = telefono;
		this.fechaNacimiento = fechaNacimiento;
		this.calleNumero = calleNumero;
		this.municipio = municipio;
		this.colonia = colonia;
		this.codigoPostal = codigoPostal;
		this.contrasena = contrasena;
		this.tiposUsuario = tiposUsuario;
		this.fechaRegistro = fechaRegistro;
		setSubindice(this.tiposUsuario.getId());
		}

	public Usuarios() {

	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
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

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getCalleNumero() {
		return calleNumero;
	}

	public void setCalleNumero(String calleNumero) {
		this.calleNumero = calleNumero;
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

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
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

	public void setTiposUsuario(TiposUsuarios tiposUsuario) {
		this.tiposUsuario = tiposUsuario;
	}

	public LocalDateTime getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDateTime fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getSubindice() {
		return subindice;
	}

	public void setSubindice(Long tipos) {
	    if (tipos != null 
	        && tipos != null 
	        && tipos == 1L) {
	        this.subindice = "Ad";
	    } else {
	        this.subindice = "Client";
	    }
	}


	public Set<Carrito> getCarrito() {
		return carrito;
	}

	public void setCarrito(Set<Carrito> carrito) {
		this.carrito = carrito;
	}

	@Override
	public String toString() {
		return "Usuarios [usuarioId=" + usuarioId + ", nombres=" + nombres + ", apellidos=" + apellidos + ", correo="
				+ correo + ", telefono=" + telefono + ", fechaNacimiento=" + fechaNacimiento + ", calleNumero="
				+ calleNumero + ", municipio=" + municipio + ", colonia=" + colonia + ", codigoPostal=" + codigoPostal
				+ ", contrasena=" + contrasena + ", subindice=" + subindice + ", tiposUsuario=" + tiposUsuario
				+ ", fechaRegistro=" + fechaRegistro + "]";
	}

}
