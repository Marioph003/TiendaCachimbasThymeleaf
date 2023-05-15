package dam.thymeleaf.cachimba.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dam.thymeleaf.cachimba.model.Complemento;
import dam.thymeleaf.cachimba.repositories.ComplementoRepository;


@Service
public class ComplementoService {

	@Autowired
	private ComplementoRepository repositorio;
	
	public List<Complemento> findAll() {
		return repositorio.findAll();
	}	
	
	public List<Complemento> findStock() {
		return repositorio.findStock();
	}
	
	public Complemento save(Complemento categoria) {
		return repositorio.save(categoria);
	}
	
	public Complemento findById(Long id) {
		return repositorio.findById(id).orElse(null);
	}
	
	public Complemento delete(Complemento complemento) {
		Complemento result = findById(complemento.getId());
		repositorio.delete(result);
		return result;
	}
	
	

}
