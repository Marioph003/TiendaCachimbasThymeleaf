package dam.thymeleaf.cachimba.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dam.thymeleaf.cachimba.model.Complemento;


public interface ComplementoRepository extends JpaRepository<Complemento, Long> {
	@Query("select c from Complemento c where c.stock = true")
	public List<Complemento> findStock();
}
