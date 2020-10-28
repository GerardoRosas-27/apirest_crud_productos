package com.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.entity.Producto;
import com.crud.repository.IProductoRepository;

@Service
public class ProductoService {
	
	@Autowired
	IProductoRepository productoRepo;
	
	public List<Producto> list(){
		return productoRepo.findAll();
	}
	
	public Optional<Producto> getOneId(int id){
		return productoRepo.findById(id);
		
	}
	public Optional<Producto> getOneNombre(String nombre){
		return productoRepo.findByNombre(nombre);
	}
	public void save(Producto producto) {
		productoRepo.save(producto);
	}
	public void delete(int id) {
		productoRepo.deleteById(id);
	}
	public boolean existsById(int id ) {
		return productoRepo.existsById(id);
	}
	public boolean existsByNombre(String nombre) {
		return productoRepo.existsByNombre(nombre);
	}
	
}
