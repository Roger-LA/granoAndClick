package com.granoAndClick.granoAndClick.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.granoAndClick.granoAndClick.model.Producto;
import com.granoAndClick.granoAndClick.repository.ProductoRepository;

@Service
public class ProductoService {
private final ProductoRepository repository;
	
	@Autowired
	public ProductoService(ProductoRepository repository) {
		this.repository = repository;
	}//constructor
	
	public List<Producto> getProductos(){
		return repository.findAll();
	}//getproductos
	
	public Producto getProducto(long id) {
		return repository.findById(id).orElseThrow(
				()-> new IllegalArgumentException("El producto con el id [" + id + "] no existe"));
	}//getproduct

	public Producto addProducto(Producto producto) {
		Optional<Producto> prod = repository.findByNombre(producto.getNombre());
		if(prod.isEmpty()) {
			repository.save(producto);
			return producto;
		}//if is empty
		return producto;
	}//addproduct

	public Producto deleteProducto(long id) {
		Producto tmp = null;
		if(repository.existsById(id)) {
			tmp = repository.findById(id).get();
			repository.deleteById(id);
		}//ifexists
		return tmp;
	}//deleteproduct

	public Producto updateProduct(long id, String nombre, String descripcion, String imagen, Double precio) {
		Producto tmp = null;
			if(repository.existsById(id)) {
				Producto prod = repository.findById(id).get();
				if (nombre !=null) prod.setNombre(nombre);
				if (descripcion !=null) prod.setDescripcion(descripcion);
				if (imagen !=null) prod.setImagen_url(imagen);
				if (precio !=null) prod.setPrecio(precio);
				repository.save(prod);
				tmp = prod;
			}//ifexists
		return tmp;
	}//updateProducto


}//classProductoService
