package com.granoAndClick.granoAndClick.dto;

public class LoginDTO {
	private Long usuarioId;
	private String nombre;
	private String correo;
	private Long tipoUsuarioId;
	private String tipoUsuarioNombre;
	private String subindice;
	
	public LoginDTO(Long usuarioId, String nombre, String correo, Long tipoUsuarioId, String tipoUsuarioNombre,
			String subindice) {
		this.usuarioId = usuarioId;
		this.nombre = nombre;
		this.correo = correo;
		this.tipoUsuarioId = tipoUsuarioId;
		this.tipoUsuarioNombre = tipoUsuarioNombre;
		this.subindice = subindice;
	}//Constructor

	public Long getUsuarioId() {
		return usuarioId;
	}

	public String getNombre() {
		return nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public Long getTipoUsuarioId() {
		return tipoUsuarioId;
	}

	public String getTipoUsuarioNombre() {
		return tipoUsuarioNombre;
	}

	public String getSubindice() {
		return subindice;
	}
}
