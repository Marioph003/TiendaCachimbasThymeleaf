package dam.thymeleaf.cachimba.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import dam.thymeleaf.cachimba.model.Cachimba;
import dam.thymeleaf.cachimba.repositories.CachimbaRepository;
import dam.thymeleaf.cachimba.services.CachimbaService;
import dam.thymeleaf.cachimba.services.ComplementoService;


@Controller
public class MainController {

	@Autowired
	private ComplementoService complementoService;

	@Autowired
	private CachimbaService cachimbaService;

	@GetMapping("/")
	public String index(@RequestParam(name="idCategoria", required=false) Long idCategoria, Model model) {
		List<Cachimba> cachimbas;

		if(idCategoria==null) {
			cachimbas = cachimbaService.obtenerProductosAleatorios(CachimbaRepository.PRODUCTOS_ALEATORIOS);
		}else {
			cachimbas = cachimbaService.findAllByCategoria(idCategoria);
		}

		model.addAttribute("categorias", complementoService.findAll());

		model.addAttribute("productos", cachimbas);

		return "index";
	}
	@GetMapping("/producto/{id}")
	public String showDetails(@PathVariable("id") Long id, Model model) {
		Cachimba cachimba = cachimbaService.findById(id);
		if(cachimba!=null) {
			model.addAttribute(cachimba);
			return "detail";
		}
		return "redirect:/";
	}
}
