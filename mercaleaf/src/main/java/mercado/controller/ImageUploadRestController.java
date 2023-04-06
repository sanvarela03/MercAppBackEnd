package mercado.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = { "http://localhost:4200" })
@RequestMapping("/api")
public class ImageUploadRestController {

	@PostMapping("/pruebafoto")
	public String recibirFoto(@RequestParam("foto") MultipartFile foto) {

		if (!foto.isEmpty()) {
			System.out.println("LA IMAGEN LLEGO PROO nombre: " + foto.getName() + "size: " + foto.getSize());
//			Path directorioRecursos = Paths.get("src//main//resources//static/uploads");
//			String rootPath = directorioRecursos.toFile().getAbsolutePath();
			String nombreDeArchivoUnico  = UUID.randomUUID().toString() + "_" + foto.getOriginalFilename();
			Path rootPath = Paths.get("uploads").resolve(nombreDeArchivoUnico);

			Path rootAbsolutePath = rootPath.toAbsolutePath();
			try {
//				byte[] bytes = foto.getBytes();
//				Path rutaCompleta = Paths.get(rootPath + "//" + foto.getOriginalFilename());
//				Files.write(rutaCompleta, bytes);
				
				Files.copy(foto.getInputStream(), rootAbsolutePath);
				System.out.println("Guardada en directorio : " + rootAbsolutePath + "nombre : " + nombreDeArchivoUnico);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "archivo cargado";
	}

	@GetMapping(value = "/verfoto/{filename:.+}",produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> verFoto(@PathVariable String filename) {

		Path pathFoto = Paths.get("uploads").resolve(filename).toAbsolutePath();
		System.out.println("pathFoto: " + pathFoto);
		
		Resource recurso = null;
		try {
			recurso = new UrlResource(pathFoto.toUri());
			
//				recurso.getFile();
			
			if (!recurso.exists() || !recurso.isReadable()) {
				throw new RuntimeException("Error: no se puede cargar la imagen: " + pathFoto.toString());
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		InputStream is = null;
		try {
			is = recurso.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] bytes = null;
		try {
			bytes = IOUtils.toByteArray(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ResponseEntity<Resource> r = ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+recurso.getFilename()+"\"").body(recurso);
		
		return new ResponseEntity<byte[]>(bytes, HttpStatus.OK);
	}
}
