package dam.thymeleaf.cachimba.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dam.thymeleaf.cachimba.model.Usuario;

@Service
public interface UsuarioService {
	List<Usuario> findAll();
	Optional<Usuario> findById(Integer id);
	Usuario save(Usuario usuario);
	Optional<Usuario> findByEmail(String email);
}
