package mercado.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import mercado.model.dao.imagen.ImageRepository;
import mercado.model.dao.usuario.IUsuarioDAO;
import mercado.model.entity.ImageModel;
import mercado.model.entity.Usuario;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "image")
public class ImageUploadController {
	
	@Autowired
	private ImageRepository imageRepository;
	
	@Autowired
	private IUsuarioDAO usuaioDAO;
	
	@PostMapping("/upload/{id}")	
	public BodyBuilder uploadImage(@RequestParam("imageFile") MultipartFile file, @PathVariable String id) throws IOException {
		
		Usuario usuario = usuaioDAO.get(Long.parseLong(id));
		
		
		System.out.println("USUARIO: "+usuario.getNombre());
	
		
		System.out.println("SE HA RECIBIDO DEL ID: "+id);
		System.out.println("Tamaño en bytes de la imagen original - " + file.getBytes().length);
		
		ImageModel img = new ImageModel(file.getOriginalFilename(), file.getContentType(), compressBytes(file.getBytes()));
		
		img.setUsuario(usuario);
		imageRepository.save(img);
		return ResponseEntity.status(HttpStatus.OK);
	}
	
	@GetMapping(path = { "/get/{idUsuario}" })
	public ImageModel getImage(@PathVariable("idUsuario") String idUsuario) {
		
		final ImageModel retrivedImage = imageRepository.encontrarPorUsuario(Long.parseLong(idUsuario)).get(0);
		ImageModel img = new ImageModel(retrivedImage.getName(), retrivedImage.getType(), decompressBytes(retrivedImage.getPicByte()));
		
		return img;
	} 
	
	
	//Comprime los bytes de la imagen antes de almacenarla en la base de datos
	public static byte[] compressBytes(byte[] data) {
		
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		
		while(!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		
		try {
			outputStream.close();
		}catch (IOException e) {
			System.out.println("nope...");
		}
		
		System.out.println("Tamaño de bytes de imagen comprimidos a - " + outputStream.toByteArray().length);
		
		return outputStream.toByteArray();
	}
	
	//Descomprime los bytes de la imagen antes de devolverla a Angular
	public static byte[] decompressBytes(byte[] data) {
		
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		
		byte[] buffer = new byte[1024];
		
		try {
			while(!inflater.finished()) {
				int count = inflater.inflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			
			outputStream.close();
			
		}catch(IOException ioe) {
			
		}catch(DataFormatException e) {
			
		}
		
		return outputStream.toByteArray();
	}
	
	
}
