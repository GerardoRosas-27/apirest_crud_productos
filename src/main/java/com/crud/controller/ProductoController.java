package com.crud.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.dto.Mensaje;
import com.crud.dto.ProductoDto;
import com.crud.entity.Producto;
import com.crud.service.ProductoService;

@RestController
@RequestMapping("/productos")
@CrossOrigin(origins = "*")
public class ProductoController {
	@Autowired
	ProductoService productoService;

	@GetMapping("/list")
	public ResponseEntity<List<Producto>> list() {
		List<Producto> lista = productoService.list();
		return new ResponseEntity<List<Producto>>(lista, HttpStatus.OK);
	}

	@GetMapping("/detail/{id}")
	public ResponseEntity<Producto> detailId(@PathVariable("id") int id) {
		if (!productoService.existsById(id)) {
			Mensaje mensaje = new Mensaje("No existe");
			return new ResponseEntity(mensaje, HttpStatus.NOT_FOUND);
		} else {
			Producto producto = productoService.getOneId(id).get();
			return new ResponseEntity<Producto>(producto, HttpStatus.OK);
		}
	}

	@GetMapping("/detailName/{nombre}")
	public ResponseEntity<Producto> detailNombre(@PathVariable("nombre") String nombre) {
		if (!productoService.existsByNombre(nombre)) {
			return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
		} else {
			Producto producto = productoService.getOneNombre(nombre).get();
			return new ResponseEntity<Producto>(producto, HttpStatus.OK);
		}
	}

	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody ProductoDto productoDto) {
		if (StringUtils.isAllBlank(productoDto.getNombre())) {
			return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		}
		if (productoDto.getPrecio() < 0) {
			return new ResponseEntity(new Mensaje("el precio debe ser mayor a 0"), HttpStatus.BAD_REQUEST);

		}
		if (productoService.existsByNombre(productoDto.getNombre())) {
			return new ResponseEntity(new Mensaje("el nombre ya existe"), HttpStatus.BAD_REQUEST);

		} else {
			Producto producto = new Producto(productoDto.getNombre(), productoDto.getPrecio());
			try {
				productoService.save(producto);
				return new ResponseEntity(new Mensaje("el producto se creo"), HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity(new Mensaje("error al crear el producto"), HttpStatus.BAD_REQUEST);
			}

		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody ProductoDto productoDto) {

		if (!productoService.existsById(id)) {
			Mensaje mensaje = new Mensaje("No existe");
			return new ResponseEntity(mensaje, HttpStatus.NOT_FOUND);
		} else {
			
			if (productoService.existsByNombre(productoDto.getNombre()) && productoService.getOneNombre(productoDto.getNombre()).get().getId() != id) {
				return new ResponseEntity(new Mensaje("el nombre ya existe"), HttpStatus.BAD_REQUEST);

			} else {
				if (StringUtils.isAllBlank(productoDto.getNombre())) {
					return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
				}else{
					if (productoDto.getPrecio() < 0) {
						return new ResponseEntity(new Mensaje("el precio debe ser mayor a 0"), HttpStatus.BAD_REQUEST);

					}else {
						Producto producto = productoService.getOneId(id).get();
						producto.setNombre(productoDto.getNombre());
						producto.setPrecio(productoDto.getPrecio());
						try {
							productoService.save(producto);
							return new ResponseEntity(new Mensaje("el producto se actualizo"), HttpStatus.OK);
						} catch (Exception e) {
							/*return new ResponseEntity(new Mensaje("error al actualizar el producto"), HttpStatus.BAD_REQUEST);*/
							return new ResponseEntity<>(new Mensaje("error al actualizar"), HttpStatus.OK);

						}
					}
				}
			}

		}

	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) {
		if (!productoService.existsById(id)) {
			return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
		} else {
			try {
				productoService.delete(id);
				return new ResponseEntity(new Mensaje("El producto se elimino"), HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity(new Mensaje("eror al eliminar el producto"), HttpStatus.BAD_REQUEST);
			}
			
		}
	}

}
