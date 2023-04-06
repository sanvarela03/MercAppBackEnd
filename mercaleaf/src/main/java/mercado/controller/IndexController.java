package mercado.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mercado.model.entity.Usuario;

@Controller
@RequestMapping(value = "/app")
public class IndexController {

	@RequestMapping(value = { "/index", "/", "/home" }, method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("titulo", "Hola spring framework");
		return "index";
	}

	@GetMapping("/perfil")
	public String perfil(Model model) {

		Usuario usuario = new Usuario();

		usuario.setNombre("Andres Felipe");
		usuario.setApellido("Celeita");
		usuario.setEmail("random@gmail.com");

		model.addAttribute("usuario", usuario);

		model.addAttribute("titulo", "Perfil de usuario:".concat(usuario.getNombre()));

		return "perfil";
	}

	@GetMapping("/listar")
	public String listar(Model model) {

		model.addAttribute("titulo", "Listado de usuarios");

		return "listar";
	}

	@ModelAttribute("usuarios")
	public List<Usuario> poblarUsuarios() {
		List<Usuario> usuarios = new ArrayList<>();
		usuarios.add(new Usuario("Andres", "Celita", "random@gamil.com"));
		usuarios.add(new Usuario("John", "Alvares", "random1@gamil.com"));
		usuarios.add(new Usuario("Danilo", "Gonzales", "random2@gamil.com"));

		return usuarios;
	}

}
