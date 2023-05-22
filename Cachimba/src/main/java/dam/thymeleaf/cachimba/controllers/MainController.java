package dam.thymeleaf.cachimba.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dam.thymeleaf.cachimba.model.Producto;
import dam.thymeleaf.cachimba.services.ProductoService;


@Controller
@RequestMapping("/admin")
public class MainController {
	
	@Autowired
	private ProductoService productoService;

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
}
