package mercado.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import mercado.model.dao.rol.IRolDAO;
import mercado.model.dao.usuario.IUsuarioDAO;
import mercado.model.entity.Usuario;

import javax.validation.Valid;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
@RequestMapping("/crud")
public class UsuarioController {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	@Qualifier("UsuarioDaoImpl-1")
	private IUsuarioDAO usuarioDao;

	@Autowired
	private IRolDAO rolDao;

	@GetMapping(value = "/uploads/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename) {
		
		Path pathFoto = Paths.get("uploads").resolve(filename).toAbsolutePath();
		
		log.info("pathFoto: " + pathFoto);
		
		Resource recurso = null;

		try {
			recurso = new UrlResource(pathFoto.toUri());
			if (!recurso.exists() || !recurso.isReadable()) {
				throw new RuntimeException("Error: no se puede cargar la imagen" + pathFoto.toString());
			}
		} catch (MalformedURLException e) {

			e.printStackTrace();
		}
		
		return ResponseEntity.ok().header(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename()+"\"").body(recurso);
	}
	
	
	

	@GetMapping("/listar")
	public String listar(Model model) {

		model.addAttribute("titulo", "Listado de Usuarios");
		model.addAttribute("usuarios", usuarioDao.getAll());

		return "crud/listar";
	}

	@GetMapping("/form")
	public String crearUsuario(Model model) {

		Usuario usuario = new Usuario();

		model.addAttribute("usuario", usuario);
		model.addAttribute("titulo", "Formulario de cliente");
		model.addAttribute("roles", rolDao.getAll());

		return "crud/form";
	}

	@PostMapping("/form")
	public String procesarUsuario(@Valid Usuario usuario, BindingResult result, Model model,
			@RequestParam("file") MultipartFile foto) {

//		if (result.hasErrors()){
//			return "crud/form";
//		}

		if (!foto.isEmpty()) {

			String uniqueFileName = UUID.randomUUID().toString() + "_" + foto.getOriginalFilename();

			Path rootPath = Paths.get("uploads").resolve(uniqueFileName);

			Path rootAbsolutPath = rootPath.toAbsolutePath();

			log.info("rootPath: " + rootPath);
			log.info("rootAbsolutPath: " + rootAbsolutPath);

			try {
				Files.copy(foto.getInputStream(), rootAbsolutPath);
				usuario.setFoto(uniqueFileName);

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		usuarioDao.save(usuario);
		return "redirect:/crud/listar";
	}

}
