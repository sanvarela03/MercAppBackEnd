package mercado.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.MultipartConfigElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;

import mercado.models.dao.usuario.IUsuarioDAO;
import mercado.models.entity.Usuario;

@RestController
@CrossOrigin(origins = { "http://localhost:4200" })
@RequestMapping("/api")
public class UsuarioRestController {

	@Autowired
	private IUsuarioDAO usuarioDAO;


	@GetMapping("/usuarios")
	public List<Usuario> index() {
		return usuarioDAO.getAll();
	}

	@GetMapping("/usuarios/{id}")
	public Usuario show(@PathVariable Long id) {
		return usuarioDAO.get(id);
	}

	@PostMapping("/usuarios")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario create(@RequestBody Usuario usuario) {

		return usuarioDAO.save(usuario);
	}

	@PostMapping(value = "/usuarios/foto", consumes = "multipart/form-data")
	public ResponseEntity<String> foto(@RequestParam("file") MultipartFile foto, @RequestParam("id") String id) {

		Usuario usuario = usuarioDAO.get(Long.parseLong(id));
		String message = "";

		if (!foto.isEmpty()) {

			String uniqueFileName = UUID.randomUUID().toString() + "_" + foto.getOriginalFilename();

			Path rootPath = Paths.get("uploads").resolve(uniqueFileName);

			Path rootAbsolutPath = rootPath.toAbsolutePath();

			try {
				Files.copy(foto.getInputStream(), rootAbsolutPath);
				usuario.setFoto(uniqueFileName);
				message = "You successfully uploaded " + foto.getOriginalFilename() + "!";
				return ResponseEntity.status(HttpStatus.OK).body(message);

			} catch (IOException e) {
				e.printStackTrace();
				message = "FAIL to upload " + foto.getOriginalFilename() + "!";
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
			}

		}
		message = "No foto " + foto.getOriginalFilename() + "!";
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(message);

	}

	@PutMapping("/usuarios/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario update(@RequestBody Usuario usuario, @PathVariable Long id) {

		Usuario usuarioActual = usuarioDAO.get(id);

		usuarioActual.setNombre(usuario.getNombre());
		usuarioActual.setApellido(usuario.getApellido());

		return usuarioDAO.save(usuarioActual);
	}

	@DeleteMapping("/usuarios/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {

		usuarioDAO.delete(id);
	}
}
