package dam.thymeleaf.cachimba.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dam.thymeleaf.cachimba.model.Cachimba;
import dam.thymeleaf.cachimba.model.Complemento;


public interface CachimbaRepository extends JpaRepository<Cachimba, Long>{
	public final int PRODUCTOS_ALEATORIOS=8;
	public List<Cachimba> findByCategoria(Complemento complemento);
	
	@Query("select ca.id from Cachimba ca")
	public List<Long> obtenerIds();
	
	@Query("select ca from Cachimba ca where ca.categoria.id = ?1")
	public List<Cachimba> findByCategoriaId(Long complementoId);
	
	@Query("select count(ca) from Cachimba ca where ca.complemento = ?1")
	public int findNumProductosByCategoria(Complemento complemento);
		
}