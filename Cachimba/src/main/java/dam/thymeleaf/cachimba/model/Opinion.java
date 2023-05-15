package dam.thymeleaf.cachimba.model;

import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Opinion {
	@Id	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@CreatedDate
	private LocalDate fecha;
	
	private int puntuacion;
	
	private String comentario;
	
	@ManyToOne
	private Cachimba cachimba;

	public Opinion() {
	}
	
	public Opinion(int puntuacion) {		
		this.puntuacion = puntuacion;
	}
	
	public Opinion(int puntuacion, Cachimba cachimba, String comentario) {
		this.puntuacion = puntuacion;
		this.cachimba = cachimba;
		this.comentario = comentario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	public Cachimba getProduct() {
		return cachimba;
	}

	public void setProducto(Cachimba cachimba) {
		this.cachimba = cachimba;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
}
