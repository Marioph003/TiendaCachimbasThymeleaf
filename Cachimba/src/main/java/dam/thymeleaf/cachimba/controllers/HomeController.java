package dam.thymeleaf.cachimba.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dam.thymeleaf.cachimba.model.DetalleOrden;
import dam.thymeleaf.cachimba.model.Orden;
import dam.thymeleaf.cachimba.model.Producto;
import dam.thymeleaf.cachimba.model.Usuario;
import dam.thymeleaf.cachimba.services.DetalleOrdenService;
import dam.thymeleaf.cachimba.services.OrdenService;
import dam.thymeleaf.cachimba.services.ProductoService;
import dam.thymeleaf.cachimba.services.UsuarioService;

@Controller
@RequestMapping("/")
public class HomeController {

	private final Logger log = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private ProductoService productoService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private OrdenService ordenService;

	@Autowired
	private DetalleOrdenService detalleOrdenService;

	//Para almacenar los detalles de la orden
	private List<DetalleOrden> detalles = new ArrayList<DetalleOrden>();

	//Datos de la orden 
	Orden orden = new Orden();

	/**
	 * Metodo para el menu de inicio de la pagina
	 * @param model: Objeto de tipo model
	 * @return devuelve la ruta al archivo html home de la
	 * carpeta usuario
	 */
	@GetMapping("")
	public String home(Model model, HttpSession session) {

		log.info("Sesion del usuario: {}", session.getAttribute("idusuario"));

		model.addAttribute("productos", productoService.findAll());

		//session
		model.addAttribute("sesion", session.getAttribute("idusuario"));

		return "usuario/home";
	}

	/**
	 * Metodo para el menu del producto
	 * 
	 * @param id Es el id del producto en cuestión
	 * 
	 * @param model Objeto tipo Model
	 * 
	 * @return devuelve la ruta del archivo productohome
	 */
	@GetMapping("productohome/{id}")
	public String productoHome(@PathVariable Integer id, Model model) {
		log.info("Id producto enviado como parámetro {}",id);

		Producto producto = new Producto();
		Optional<Producto> optionalProducto = productoService.get(id);
		producto = optionalProducto.get();

		model.addAttribute("producto", producto);

		return "usuario/productohome";
	}

	/**
	 * Metodo para añadir productos al carrito
	 * 
	 * @param id: id del producto
	 * @param cantidad: cantidad de productos que quiero añadir al carrito
	 * @param model: Objeto de tipo Model
	 * 
	 * @return devuelve la ruta al archivo html carrito
	 */
	@PostMapping("/cart")
	public String addCart(@RequestParam Integer id, @RequestParam Integer cantidad, Model model) {
		DetalleOrden detalleOrden = new DetalleOrden();
		Producto producto = new Producto();
		double sumaTotal = 0;

		Optional<Producto> optionalProducto = productoService.get(id);
		log.info("Producto añadido: {}", optionalProducto.get());
		log.info("Cantidad: {}", cantidad);
		producto = optionalProducto.get();

		detalleOrden.setCantidad(cantidad);
		detalleOrden.setPrecio(producto.getPrecio());
		detalleOrden.setNombre(producto.getNombre());
		detalleOrden.setTotal(producto.getPrecio()*cantidad);
		detalleOrden.setProducto(producto);

		//validar que el producto no se añada 2 veces
		Integer idProducto = producto.getId();
		boolean ingresado=detalles.stream().anyMatch(p -> p.getProducto().getId()==idProducto);

		if(!ingresado) {
			detalles.add(detalleOrden);
		}

		sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();

		orden.setTotal(sumaTotal);
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden); 

		return "usuario/carrito";
	}

	/**
	 * Metodo para quitar un producto del carrito
	 * 
	 * @param id: Id del producto
	 * @param model: Objeto de tipo Model
	 * 
	 * @return devuelve la ruta al archivo html carrito
	 */
	@GetMapping("/delete/cart/{id}")
	public String deleteProductoCart(@PathVariable Integer id, Model model) {

		//Lista nueva de productos
		List<DetalleOrden> ordenesNuevas = new ArrayList<DetalleOrden>();

		for (DetalleOrden detalleOrden : detalles) {
			if(detalleOrden.getProducto().getId()!=id) {
				ordenesNuevas.add(detalleOrden);
			}
		}
		//poner la nueva lista con los productos restantes
		detalles = ordenesNuevas;

		double sumaTotal = 0;
		sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();

		orden.setTotal(sumaTotal);
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);

		return "usuario/carrito";
	}

	/**
	 * Metodo para obtener productos del carrito
	 * 
	 * @param model: Objeto tipo Model
	 *  
	 * @return: ruta del archivo html carrito
	 */
	@GetMapping("/getCart")
	public String getCart(Model model) {

		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);

		return "/usuario/carrito";
	}

	@GetMapping("/order")
	public String order(Model model) {

		Usuario usuario = usuarioService.findById(1).get();

		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);
		model.addAttribute("usuario", usuario);

		return "usuario/resumenorden";
	}
	// guardar la orden
	@GetMapping("/saveOrder")
	public String saveOrder(HttpSession session ) {
		Date fechaCreacion = new Date();
		orden.setFechaCreacion(fechaCreacion);
		orden.setNumero(ordenService.generarNumeroOrden());

		//usuario
		Usuario usuario =usuarioService.findById( Integer.parseInt(session.getAttribute("idusuario").toString())  ).get();

		orden.setUsuario(usuario);
		ordenService.save(orden);

		//guardar detalles
		for (DetalleOrden dt:detalles) {
			dt.setOrden(orden);
			detalleOrdenService.save(dt);
		}

		///limpiar lista y orden
		orden = new Orden();
		detalles.clear();

		return "redirect:/";
	}

	@PostMapping("/search")
	public String searchProduct(@RequestParam String nombre,@RequestParam String descripcion, Model model) {
		log.info("Nombre del producto: {}", nombre);
		List<Producto> productos= productoService.findAll().stream().filter( p -> p.getNombre().contains(nombre) && 
				p.getDescripcion().contains(descripcion))
				.collect(Collectors.toList());
		model.addAttribute("productos", productos);		
		return "usuario/home";
	}

}
