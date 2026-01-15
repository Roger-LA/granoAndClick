package com.granoAndClick.granoAndClick.service;

import com.granoAndClick.granoAndClick.dto.CarritoDTO;
import com.granoAndClick.granoAndClick.dto.CarritoDetalleDTO;
import com.granoAndClick.granoAndClick.dto.CarritoResponseDTO;
import com.granoAndClick.granoAndClick.model.Carrito;
import com.granoAndClick.granoAndClick.model.CarritoDetalle;
import com.granoAndClick.granoAndClick.model.Producto;
import com.granoAndClick.granoAndClick.model.Usuarios;
import com.granoAndClick.granoAndClick.repository.CarritoRepository;
import com.granoAndClick.granoAndClick.repository.ProductoRepository;
import com.granoAndClick.granoAndClick.repository.UsuariosRepository;
import com.granoAndClick.granoAndClick.repository.CarritoDetalleRepository; // Asegúrate de importar el repositorio de CarritoDetalle
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CarritoService {

	
	private final CarritoRepository repository;
	private final CarritoDetalleRepository detalleRepository; 
	private final ProductoRepository productoRepository;
	
	private final UsuariosRepository usuariosRepository;
	@Autowired
	public CarritoService(CarritoRepository repository, CarritoDetalleRepository detalleRepository,
			ProductoRepository productoRepository, UsuariosRepository usuariosRepository) {
		super();
		this.repository = repository;
		this.detalleRepository = detalleRepository;
		this.productoRepository = productoRepository;
		this.usuariosRepository = usuariosRepository;
	}

	
	

	public List<Carrito> getAllCarritos() {
		return repository.findAll();
	}

	public Carrito getCarrito(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("El carrito con id [" + id + "], no existe"));
	}

	

	public Carrito deleteCarrito(Long id) {
		Carrito tmp = null;
		if (repository.existsById(id)) {
			tmp = repository.findById(id).get();
			repository.deleteById(id);
		}
		return tmp;
	}

	public CarritoResponseDTO addCarrito(CarritoDTO dto) {
	    Usuarios usuario = usuariosRepository.findById(dto.getUsuarioId())
	        .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + dto.getUsuarioId()));

	    Set<CarritoDetalle> detalles = new HashSet<>();
	    for (CarritoDetalleDTO detalleDTO : dto.getDetalles()) {
	        Producto producto = productoRepository.findById(detalleDTO.getProductoId())
	            .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + detalleDTO.getProductoId()));

	        CarritoDetalle detalle = new CarritoDetalle();
	        detalle.setProducto(producto);
	        detalle.setCantidad(detalleDTO.getCantidad());
	        detalle.calcularSubtotal(); 
	        detalles.add(detalle);
	    }

	    Carrito nuevoCarrito = new Carrito(dto.getCostoEnvio(), usuario, detalles);

	    for (CarritoDetalle detalle : detalles) {
	        detalle.setCarrito(nuevoCarrito);
	    }

	    nuevoCarrito.calcularTotal();

	    Carrito carritoGuardado = repository.save(nuevoCarrito);

	    // Mapear a CarritoResponseDTO
	    Set<CarritoDetalleDTO> detalleDTOs = carritoGuardado.getDetalles().stream()
	        .map(d -> {
	            CarritoDetalleDTO dtoDetalle = new CarritoDetalleDTO();
	            dtoDetalle.setProductoId(d.getProducto().getId());
	            dtoDetalle.setCantidad(d.getCantidad());
	            dtoDetalle.setCarritoId(carritoGuardado.getCarritoId());
	            return dtoDetalle;
	        })
	        .collect(Collectors.toSet());

	    return new CarritoResponseDTO(
	        carritoGuardado.getCarritoId(),
	        carritoGuardado.getUsuarios().getUsuarioId(),
	        carritoGuardado.getCostoEnvio(),
	        detalleDTOs,
	        carritoGuardado.getTotal()
	    );
	}

	public CarritoResponseDTO updateCarrito(Long id, CarritoDTO dto) {
	    Carrito carritoExistente = repository.findById(id)
	        .orElseThrow(() -> new RuntimeException("Carrito no encontrado con id: " + id));

	    Usuarios usuario = usuariosRepository.findById(dto.getUsuarioId())
	        .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + dto.getUsuarioId()));

	    carritoExistente.setUsuarios(usuario);
	    carritoExistente.setCostoEnvio(dto.getCostoEnvio());

	    carritoExistente.getDetalles().clear();

	    for (CarritoDetalleDTO detalleDTO : dto.getDetalles()) {
	        Producto producto = productoRepository.findById(detalleDTO.getProductoId())
	            .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + detalleDTO.getProductoId()));

	        CarritoDetalle detalle = new CarritoDetalle();
	        detalle.setProducto(producto);
	        detalle.setCantidad(detalleDTO.getCantidad());
	        detalle.calcularSubtotal();
	        detalle.setCarrito(carritoExistente);

	        carritoExistente.getDetalles().add(detalle);
	    }

	    carritoExistente.calcularTotal();

	    Carrito carritoActualizado = repository.save(carritoExistente);

	    // Mapear a CarritoResponseDTO
	    Set<CarritoDetalleDTO> detalleDTOs = carritoActualizado.getDetalles().stream()
	        .map(d -> {
	            CarritoDetalleDTO dtoDetalle = new CarritoDetalleDTO();
	            dtoDetalle.setProductoId(d.getProducto().getId());
	            dtoDetalle.setCantidad(d.getCantidad());
	            dtoDetalle.setCarritoId(carritoActualizado.getCarritoId());
	            return dtoDetalle;
	        })
	        .collect(Collectors.toSet());

	    return new CarritoResponseDTO(
	        carritoActualizado.getCarritoId(),
	        carritoActualizado.getUsuarios().getUsuarioId(),
	        carritoActualizado.getCostoEnvio(),
	        detalleDTOs,
	        carritoActualizado.getTotal()
	    );
	}


}
