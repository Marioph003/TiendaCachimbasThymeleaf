package dam.thymeleaf.cachimba.services;

import org.springframework.stereotype.Service;

import dam.thymeleaf.cachimba.model.DetalleOrden;

@Service 
public interface DetalleOrdenService {
DetalleOrden save(DetalleOrden detalleOrden);
}
