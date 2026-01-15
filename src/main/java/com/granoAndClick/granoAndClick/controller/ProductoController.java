package com.granoAndClick.granoAndClick.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import com.granoAndClick.granoAndClick.model.Producto;
import com.granoAndClick.granoAndClick.service.ProductoService;

@RestController
@RequestMapping(path= "/api/productos")
@CrossOrigin(origins = "https://roger-la.github.io/", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ProductoController {
	
	private final ProductoService service;
	@Autowired
	public ProductoController(ProductoService service) {
		this.service = service;
	}//constructor
	
	@GetMapping
		public List<Producto> getProductos(){
			return service.getProductos();
	}//getProductos
	
	@GetMapping("{prodid}")
	public Producto getProducto(@PathVariable("prodid")long id){
		return service.getProducto(id);
}//getProducto
	
	@PostMapping
	public Producto addProducto(@RequestBody Producto producto) {
		return service.addProducto(producto);
	}//addProducto
	
	@DeleteMapping("{prodid}")
	public Producto deleteProducto(@PathVariable("prodid") long id) {
		return service.deleteProducto(id);
	}//deleteProducto
	
	@PutMapping("/productos/{prodid}")
	public Producto updateProducto(@PathVariable("prodid")long id,
			@RequestParam(name = "nombre", required = false) String nombre,
			@RequestParam(name = "descripcion", required = false) String descripcion,
			@RequestParam(name = "imagen", required = false) String imagen,
			@RequestParam(name = "precio", required = false) Double precio
			) {
		return service.updateProduct(id, nombre, descripcion, imagen, precio);
	}//updateProducto
	
}//classProductoController

