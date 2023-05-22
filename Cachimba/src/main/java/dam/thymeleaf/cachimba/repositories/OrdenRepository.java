package dam.thymeleaf.cachimba.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dam.thymeleaf.cachimba.model.Orden;
import dam.thymeleaf.cachimba.model.Usuario;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Integer>{
List<Orden> findByUsuario(Usuario usuario);
}
