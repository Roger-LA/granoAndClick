package com.granoAndClick.granoAndClick.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.granoAndClick.granoAndClick.dto.ChangePassword;
import com.granoAndClick.granoAndClick.model.Usuarios;
import com.granoAndClick.granoAndClick.repository.UsuariosRepository;

@Service
public class UsuariosService {

	private final UsuariosRepository usuarioRep;
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	public UsuariosService(UsuariosRepository usuarioRep) {
		this.usuarioRep = usuarioRep;
	}
	public List<Usuarios> getUsuarios() {
		return usuarioRep.findAll();
	}
	public Usuarios getUsuario(long id) {
		return usuarioRep.findById(id).orElseThrow(
		() -> new IllegalArgumentException("El usuario con id ["+ id +"], no existe"));
	}
	
	public Usuarios addUsuarios(Usuarios usuario) {
		Optional<Usuarios> user = usuarioRep.findByCorreo(usuario.getCorreo());
		if(user.isEmpty()) {
			usuario.setContrasena(encoder.encode(usuario.getContrasena()));
			usuarioRep.save(usuario);
			return usuario;
		}
		return null;
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
			//			if(user.getPassword().equals(changePassword.getPassword())) {
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
}

