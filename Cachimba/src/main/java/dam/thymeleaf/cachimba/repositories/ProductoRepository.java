package dam.thymeleaf.cachimba.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dam.thymeleaf.cachimba.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer>{

}
