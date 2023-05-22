package dam.thymeleaf.cachimba.controllers;

import java.util.Optional;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import dam.thymeleaf.cachimba.model.Producto;
import dam.thymeleaf.cachimba.model.Usuario;
import dam.thymeleaf.cachimba.services.ProductoService;
import dam.thymeleaf.cachimba.services.UploadFileService;

@Controller
@RequestMapping("/productos")
public class ProductoController {
	private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);

	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private UploadFileService upload;
	
	/**
	 * Metodo para mostrar productos
	 * @return devuelve la ruta del archivo html al que va a acceder
	 */
	@GetMapping("")
	public String show(Model model) {
		model.addAttribute("productos", productoService.findAll());
		return "productos/show";
	}
	
	/**
	 * Metodo para crear productos
	 * @return devuelve la ruta del archivo html al que va a acceder
	 */
	@GetMapping("/create")
	public String create() {
		return "productos/create";
	}
	
	/**
	 * Metodo para guardar un producto
	 * 
	 * @param Toma como parametro un objeto de tipo producto
	 * @return devuelve un redirect a la carpeta productos
	 */
	@PostMapping("/save")
	public String save(Producto producto,@RequestParam("img") MultipartFile file) {
		LOGGER.info("Este es el objeto producto {}",producto);
		Usuario usuario = new Usuario(1, "", "", "", "", "", "", "");
		producto.setUsuario(usuario);
		
		//imagen
		if(producto.getId()==null) { //cuando se crea un producto
			String nombreImagen = upload.saveImage(file);
			producto.setImagen(nombreImagen);
		} else {
			if(file.isEmpty()) { // editamos el producto pero no cambiamos la imagen
				Producto p = new Producto();
				p = productoService.get(p.getId()).get();
				p.setImagen(p.getImagen());
			} else {
				String nombreImagen = upload.saveImage(file);
				producto.setImagen(nombreImagen);
			}
		}
		
		productoService.save(producto);
		return "redirect:/productos";
	}
	/**
	 * Metodo para editar productos
	 * 
	 * @param id Parametro tipo Integer para especificar el id del producto
	 * con la anotacion PathVariable que modifica el valor {id} al tipo del
	 * parámetro
	 * 
	 * @return: Devuelve la ruta al archivo html edit
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model model) {
		Producto producto = new Producto();
		Optional<Producto> optionalProducto = productoService.get(id);
		producto = optionalProducto.get();
		
		LOGGER.info("Producto buscado: {}",producto);
		model.addAttribute("producto", producto);
		
		return "productos/edit";
	}
	/**
	 * Metodo para actualizar productos
	 * 
	 * @param producto Objeto de tipo producto
	 * @return devuelve un redirect a la carpeta productos
	 */
	@PostMapping("/update")
	public String update(Producto producto, @RequestParam("img") MultipartFile file) {
		
		if(file.isEmpty()) { // editamos el producto pero no cambiamos la imagen
			Producto p = new Producto();
			p = productoService.get(p.getId()).get();
			p.setImagen(p.getImagen());
		} else { //cuando se edita tbn la imagen
			Producto p = new Producto();
			p = productoService.get(p.getId()).get();
			
			//eliminar cuando no sea la imagen por defecto
			if(!p.getImagen().equals("default.jpg")) {
				upload.deleteImage(p.getImagen());
			}
			
			String nombreImagen = upload.saveImage(file);
			producto.setImagen(nombreImagen);
		}
		
		productoService.update(producto);
		return "redirect:/productos";
	}
	/**
	 * Metodo para eliminar un producto
	 * @return devuelve un redirect a la carpeta productos
	 */
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		
		Producto producto = new Producto();
		producto = productoService.get(id).get();
		
		//eliminar cuando no sea la imagen por defecto
		if(!producto.getImagen().equals("default.jpg")) {
			upload.deleteImage(producto.getImagen());
		}
		
		productoService.delete(id);
		return "redirect:/productos";
	}
}
