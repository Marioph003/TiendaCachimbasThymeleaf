package dam.thymeleaf.cachimba.services;

import java.util.List;
import java.util.Optional;

import dam.thymeleaf.cachimba.model.Producto;

public interface ProductoService {
	//Metodo para guardar productos
	public Producto save(Producto producto);
	//Optional da la opcion de validar si el objeto de la base de datos 
	//existe o no
	//Metodo para obtener productos
	public Optional<Producto> get(Integer id);
	//Metodo para actualizar productos
	public void update(Producto producto);
	//Metodo para eliminar producto
	public void delete(Integer id);
	//Metodo para buscar productos
	public List<Producto> findAll();
}
