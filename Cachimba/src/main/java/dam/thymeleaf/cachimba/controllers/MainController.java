package dam.thymeleaf.cachimba.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import dam.thymeleaf.cachimba.model.Orden;
import dam.thymeleaf.cachimba.model.Producto;
import dam.thymeleaf.cachimba.services.OrdenService;
import dam.thymeleaf.cachimba.services.ProductoService;
import dam.thymeleaf.cachimba.services.UsuarioService;


@Controller
@RequestMapping("/admin")
public class MainController {
	
	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private OrdenService ordensService;
	
	private Logger logg= LoggerFactory.getLogger(MainController.class);

	/**
	 * Metodo para el inicio de la pagina
	 * @param model: Objeto de tipo Model
	 * @return: Devuelve la ruta a el archivo html de home
	 */
	//Para que me redireccione
	@GetMapping("")
	public String home(Model model) { 
		
		List<Producto> productos = productoService.findAll();
		model.addAttribute("productos", productos);
		
		return "admin/home";	
	}
	
	@GetMapping("/usuarios")
	public String usuarios(Model model) {
		model.addAttribute("usuarios", usuarioService.findAll());
		return "administrador/usuarios";
	}
	
	@GetMapping("/ordenes")
	public String ordenes(Model model) {
		model.addAttribute("ordenes", ordensService.findAll());
		return "administrador/ordenes";
	}
	
	@GetMapping("/detalle/{id}")
	public String detalle(Model model, @PathVariable Integer id) {
		logg.info("Id de la orden {}",id);
		Orden orden= ordensService.findById(id).get();
		
		model.addAttribute("detalles", orden.getDetalles());
		
		return "administrador/detalleorden";
	}
}
