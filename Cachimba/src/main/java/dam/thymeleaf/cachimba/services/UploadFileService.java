package dam.thymeleaf.cachimba.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Clase para subir imagenes
 * @author Mario
 * @version 1.0
 */
@Service
public class UploadFileService {
	private String folder = "images//";

	/**
	 * Metodo para guardar imagenes
	 * 
	 * @param file: Se le pasa el nombre de un archivo por parametro
	 * 
	 * @return: Devuelve el nombre original del archivo en el primer return
	 * y en el segundo una imagren por defecto
	 */
	public String saveImage(MultipartFile file) {
		if(!file.isEmpty()) {
			try {
				byte [] bytes = file.getBytes();
				Path path = Paths.get(folder+file.getOriginalFilename());
				Files.write(path, bytes);
				return file.getOriginalFilename();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "default.jpg";
	}
	
	public void deleteImage(String nombre) {
		String ruta = "images//";
		File file = new File(ruta+nombre);
		file.delete();
	}
}
