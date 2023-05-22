package dam.thymeleaf.cachimba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dam.thymeleaf.cachimba.model.DetalleOrden;
import dam.thymeleaf.cachimba.repositories.DetalleOrdenRepository;

@Service
public class DetalleOrdenServiceImpl implements DetalleOrdenService{

	@Autowired
	private DetalleOrdenRepository detalleOrdenRepository;
	
	@Override
	public DetalleOrden save(DetalleOrden detalleOrden) {
		return detalleOrdenRepository.save(detalleOrden);
	}

}
