package dam.thymeleaf.cachimba.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dam.thymeleaf.cachimba.model.Orden;
import dam.thymeleaf.cachimba.model.Usuario;

@Service
public interface OrdenService {
	List<Orden> findAll();
	Optional<Orden> findById(Integer id);
	Orden save(Orden orden);
	String generarNumeroOrden();
	List<Orden> findByUsuario(Usuario usuario);

}
