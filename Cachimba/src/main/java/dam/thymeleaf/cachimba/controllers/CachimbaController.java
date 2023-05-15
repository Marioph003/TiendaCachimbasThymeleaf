package dam.thymeleaf.cachimba.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dam.thymeleaf.cachimba.model.Cachimba;
import dam.thymeleaf.cachimba.services.CachimbaService;
import dam.thymeleaf.cachimba.services.ComplementoService;


/**
 * 
 * @author usuario
 *
 */
@Controller
@RequestMapping("/admin/cachimba")
public class CachimbaController {

	@Autowired
	private CachimbaService cachimbaService;

	@Autowired
	private ComplementoService complementoService;

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("cachimbas", cachimbaService.findAll());
		return "admin/list-cachimba";
	}	
	@GetMapping("/nuevo")
	public String nuevoProducto(Model model) {
		model.addAttribute("cachimba",new Cachimba());
		model.addAttribute("complementos",this.complementoService.findAll());
		return "admin/form-cachimba";
	}

	@PostMapping("/nuevo/submit")
	public String submitNuevoProducto(@Validated Cachimba cachimba, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("complemento", complementoService.findAll());
			return "admin/form-cachimba";
		} else {
			cachimbaService.save(cachimba);
			return "redirect:/admin/cachimba/";

		}

	}

	@GetMapping("/editar/{id}")
	public String editarProducto(@PathVariable("id") Long id, Model model) {

		Cachimba cachimba = cachimbaService.findById(id);

		if (cachimba != null) {
			model.addAttribute("cachimba", cachimba);
			model.addAttribute("complemento", complementoService.findAll());
			return "admin/form-cachimba";
		} else {
			return "redirect:/admin/cachimba/";
		}

	}

	@GetMapping("/borrar/{id}")
	public String borrarProducto(@PathVariable("id") Long id, Model model) {

		Cachimba cachimba = cachimbaService.findById(id);

		if (cachimba != null) {
			cachimbaService.delete(cachimba);
		}

		return "redirect:/admin/cachimba/";

	}
}