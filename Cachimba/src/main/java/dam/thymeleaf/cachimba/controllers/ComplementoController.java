package dam.thymeleaf.cachimba.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dam.thymeleaf.cachimba.model.Complemento;
import dam.thymeleaf.cachimba.services.CachimbaService;
import dam.thymeleaf.cachimba.services.ComplementoService;



@Controller
@RequestMapping("/admin/complemento")
public class ComplementoController {
	@Autowired
	private ComplementoService complementoService;
	
	@Autowired
	private CachimbaService cachimbaService;
		
	@GetMapping("/")
	public String index(Model model) {					
		model.addAttribute("complementos", complementoService.findAll());
		return "admin/list-complementos";
	}
	
	@GetMapping("/nueva")
	public String nuevaCategoria(Model model) {
		model.addAttribute("complemento", new Complemento());
		return "admin/form-categoria";
	}
	
	@PostMapping("/nueva/submit")
	public String submitNuevaCategoria(@ModelAttribute("complemento") Complemento complemento, Model model) {
		
		complementoService.save(complemento);
		
		return "redirect:/admin/categoria/";
	}
	
	@GetMapping("/editar/{id}")
	public String editarCategoria(@PathVariable("id") Long id, Model model) {
		
		Complemento complemento = complementoService.findById(id);
		
		if (complemento != null) {
			model.addAttribute("complemento", complemento);
			return "admin/form-complemento";
		} else {
			return "redirect:/admin/complemento/";
		}
		
	}
	
	@GetMapping("/borrar/{id}")
	public String borrarCategoria(@PathVariable("id") Long id, Model model) {
		
		Complemento complemento = complementoService.findById(id);
		
		if (complemento != null) {
			
			if (cachimbaService.numeroProductosCategoria(complemento) == 0) {
				complementoService.delete(complemento);				
			} else {
				return "redirect:/admin/complemento/?error=true";
			}
			
		} 

		return "redirect:/admin/complemento/";
	}
}

