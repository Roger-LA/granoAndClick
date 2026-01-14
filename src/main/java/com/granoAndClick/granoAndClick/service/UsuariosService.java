package com.granoAndClick.granoAndClick.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.granoAndClick.granoAndClick.dto.ChangePassword;
import com.granoAndClick.granoAndClick.dto.RegistrarUsuarioDTO;
import com.granoAndClick.granoAndClick.model.TiposUsuarios;
import com.granoAndClick.granoAndClick.model.Usuarios;
import com.granoAndClick.granoAndClick.repository.TiposUsuariosRepository;
import com.granoAndClick.granoAndClick.repository.UsuariosRepository;

@Service
public class UsuariosService {

	private final UsuariosRepository usuarioRep;
	private final TiposUsuariosRepository tiposRep;
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	public UsuariosService(UsuariosRepository usuarioRep, TiposUsuariosRepository tiposRep) {
		this.usuarioRep = usuarioRep;
		this.tiposRep = tiposRep;
	}
	public List<Usuarios> getUsuarios() {
		return usuarioRep.findAll();
	}
	public Usuarios getUsuario(long id) {
		return usuarioRep.findById(id).orElseThrow(
		() -> new IllegalArgumentException("El usuario con id ["+ id +"], no existe"));
	}
	
	public Usuarios addUsuarios(RegistrarUsuarioDTO dto) {
	    // Verificar si ya existe un usuario con el mismo correo
	    if (usuarioRep.findByCorreo(dto.getCorreo()).isPresent()) {
	        return null; // correo ya registrado
	    }

	    // Validar que la contraseña no sea nula o vacía
	    if (dto.getContrasena() == null || dto.getContrasena().isBlank()) {
	        throw new IllegalArgumentException("La contraseña no puede ser nula o vacía");
	    }

	    TiposUsuarios tipo = tiposRep.findById(dto.getTipoUsuarioId())
	            .orElseThrow(() -> new IllegalArgumentException("Tipo de usuario inválido"));

	    Usuarios usuario = new Usuarios(
	        dto.getNombres(),
	        dto.getApellidos(),
	        dto.getCorreo(),
	        dto.getTelefono(),
	        dto.getFechaNacimiento(),
	        dto.getCalleNumero(),
	        dto.getMunicipio(),
	        dto.getColonia(),
	        dto.getCodigoPostal(),
	        encoder.encode(dto.getContrasena()), 
	        tipo
	    );

	    return usuarioRep.saveAndFlush(usuario);
	}

	
	public Usuarios deleteUsuario(long id) {
		Usuarios tmp = null;
		if(usuarioRep.existsById(id)) {
			tmp=usuarioRep.findById(id).get();
			usuarioRep.deleteById(id);
		}//If Exists
		return tmp;
	}//Add Usuario
	
	public Usuarios updateUsuario(Long id, ChangePassword changePassword) {
		Usuarios tmp = null;
		if(usuarioRep.existsById(id)) {
			Usuarios user = usuarioRep.findById(id).get();
			if(encoder.matches(changePassword.getPassword(), user.getContrasena())) {
				user.setContrasena(encoder.encode(changePassword.getnPassword()));
				usuarioRep.save(user);
				tmp=user;
			}
		}
		return tmp;
	}//Update Usuario
	public boolean validateUser(Usuarios usuario) {
		Optional<Usuarios> usr = usuarioRep.findByCorreo(usuario.getCorreo());
		if(usr.isPresent()) {
			Usuarios user = usr.get();
			
			if(encoder.matches(usuario.getContrasena(), user.getContrasena())) {
				return true;
			}// IF Matches
		}//If isPresent
		return false;
	}//validateUser	
	
	public Usuarios getByCorreo(String correo) {
	    return usuarioRep.findByCorreo(correo).orElse(null);
	}	
	
}

