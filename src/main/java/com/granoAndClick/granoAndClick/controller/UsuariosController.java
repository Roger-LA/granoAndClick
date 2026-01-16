package com.granoAndClick.granoAndClick.controller;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.granoAndClick.granoAndClick.dto.ChangePassword;
import com.granoAndClick.granoAndClick.dto.RecuperarDTO;
import com.granoAndClick.granoAndClick.dto.RegistrarUsuarioDTO;
import com.granoAndClick.granoAndClick.model.Usuarios;
import com.granoAndClick.granoAndClick.service.UsuariosService;
 
 
@RestController
@RequestMapping(path="/api/usuarios")
public class UsuariosController {
	private final UsuariosService uService;
	
	@Autowired
	public UsuariosController (UsuariosService uService) {
		this.uService = uService;
	}
	
	@GetMapping
	public List<Usuarios> getUsuario(){
		return uService.getUsuarios();
	}
	
	@GetMapping("{userid}")
	public Usuarios getUsuario(@PathVariable ("userid") long id) {
		return uService.getUsuario(id);
	}
	
	@PostMapping
	public Usuarios addUsuario(@RequestBody RegistrarUsuarioDTO dto) {
	    var auth = SecurityContextHolder.getContext().getAuthentication();
 
	    // Validar si se intenta registrar un admin
	    if (dto.getTipoUsuarioId() != null && dto.getTipoUsuarioId() == 1L) {
	    	if (auth == null || auth.getAuthorities().stream()
	    			.noneMatch(a -> a
	    					.getAuthority().equals("ROLE_ADMIN"))) {
	    		throw new RuntimeException("Solo un admin puede registrar otro admin");
	    		}
	    }
 
	    Usuarios nuevo = uService.addUsuarios(dto);
 
	    if (nuevo == null) {
	        throw new RuntimeException("El correo ya está registrado");
	    }
 
	    return nuevo;
	}
 
	@DeleteMapping("{userid}")
	public Usuarios deleteUsuario(@PathVariable ("userid") long id) {
		return uService.deleteUsuario(id);
	}
	
	@PutMapping(path="{userid}")
	public Usuarios updateUsuario(@PathVariable("userid") Long id,
			@RequestBody ChangePassword changePassword) {
		return uService.updateUsuario(id,changePassword);
	}
	
	@PutMapping("/recuperar-password")
	public Usuarios recuperarPassword(@RequestBody RecuperarDTO dto) {
	    return uService.recuperarPorTelefono(dto);
	}
}
 
