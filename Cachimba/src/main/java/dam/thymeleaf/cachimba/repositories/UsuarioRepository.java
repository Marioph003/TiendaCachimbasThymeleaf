package dam.thymeleaf.cachimba.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dam.thymeleaf.cachimba.model.Usuario;



@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
  
}
