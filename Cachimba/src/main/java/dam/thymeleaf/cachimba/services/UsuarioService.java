package dam.thymeleaf.cachimba.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import dam.thymeleaf.cachimba.model.Usuario;

@Service
public interface UsuarioService {
	Optional<Usuario> findById(Integer id);
}
