package com.granoAndClick.granoAndClick.dto;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;

public class RegistrarUsuarioDTO {

    private String nombres;
    private String apellidos;
    private String correo;
    private String telefono;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;

    private String calleNumero;
    private String municipio;
    private String colonia;
    private String codigoPostal;
    private String contrasena;

    private Long tipoUsuarioId;

    public RegistrarUsuarioDTO() {}

   
    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public String getCalleNumero() { return calleNumero; }
    public void setCalleNumero(String calleNumero) { this.calleNumero = calleNumero; }

    public String getMunicipio() { return municipio; }
    public void setMunicipio(String municipio) { this.municipio = municipio; }

    public String getColonia() { return colonia; }
    public void setColonia(String colonia) { this.colonia = colonia; }

    public String getCodigoPostal() { return codigoPostal; }
    public void setCodigoPostal(String codigoPostal) { this.codigoPostal = codigoPostal; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public Long getTipoUsuarioId() { return tipoUsuarioId; }
    public void setTipoUsuarioId(Long tipoUsuarioId) { this.tipoUsuarioId = tipoUsuarioId; }
}
