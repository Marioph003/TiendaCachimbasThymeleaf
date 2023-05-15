package dam.thymeleaf.cachimba.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "complementos")
public class Complemento {
	@Id
	@GeneratedValue
	private Long id;
	
	private String nombre;
	
	private String descripcion;
	
	private float precio;
	
	private String tipo;
	
	private String marca;
	
	private boolean stock;
	
	private String imagen;

	public Complemento() { }

	public Complemento(String nombre, String descripcion, float precio, String tipo, String marca,
			boolean stock, String imagen) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.tipo = tipo;
		this.marca = marca;
		this.stock = stock;
		this.imagen = imagen;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public boolean getStock() {
		return stock;
	}

	public void setStock(boolean stock) {
		this.stock = stock;
	}


	
	
	
	
	

}