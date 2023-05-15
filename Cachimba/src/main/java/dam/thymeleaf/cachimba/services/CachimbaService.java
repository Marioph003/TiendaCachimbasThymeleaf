package dam.thymeleaf.cachimba.services;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import dam.thymeleaf.cachimba.model.Cachimba;
import dam.thymeleaf.cachimba.model.Complemento;
import dam.thymeleaf.cachimba.repositories.CachimbaRepository;


public class CachimbaService {

	@Autowired
	private CachimbaRepository cachimbaRepository;
	
	public List<Cachimba> findAll() {
		return cachimbaRepository.findAll();
	}	
	
	public Cachimba save(Cachimba cachimba) {
		return cachimbaRepository.save(cachimba);
	}
	
	public List<Cachimba> findAllByCategoria(Complemento categoria) {
		return cachimbaRepository.findByCategoria(categoria);
	}
	
	public List<Cachimba> findAllByCategoria(Long categoriaId) {
		return cachimbaRepository.findByCategoriaId(categoriaId);
	}
	
	public Cachimba findById(Long id) {
		return cachimbaRepository.findById(id).orElse(null);
	}
	
	public Cachimba delete(Cachimba cachimba) {
		Cachimba result = findById(cachimba.getId());
		cachimbaRepository.delete(result);
		return result;
	}
	public int numeroProductosCategoria(Complemento complemento) {
		return cachimbaRepository.findNumProductosByCategoria(complemento);
	}
	
	
	/*
	 * Este método sirve para obtener un número de productos aleatorios.
	 * Lo realizamos en Java para abstraernos mejor de la base de datos
	 * concreta que vamos a usar.
	 * Algunos SGBDR nos permitirían usar la función RANDOM, y podríamos
	 * hacer esta consulta de forma nativa.
	 */
	public List<Cachimba> obtenerProductosAleatorios(int numero) {
		// Obtenemos los ids de todos los productos
		List<Long> listaIds = cachimbaRepository.obtenerIds();
		// Los desordenamos 
		Collections.shuffle(listaIds);
		// Nos quedamos con los N primeros, con N = numero.
		listaIds = listaIds.stream().limit(numero).collect(Collectors.toList());
		// Buscamos los productos con esos IDs y devolvemos la lista
		return cachimbaRepository.findAllById(listaIds);

	}
}
