package mercado.controller;

import mercado.model.dao.producto.IProductoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mercado.model.dao.usuario.IUsuarioDAO;
import mercado.model.entity.Usuario;

@Controller
@RequestMapping("/ingresar")
public class LoginController {
	
	@Autowired
	private IUsuarioDAO usuarioDao;

	@Autowired
	private IProductoDAO productoDAO;
	
	
	@GetMapping("/input")
	public String input(Model model) {

		model.addAttribute("titulo", "Iniciar Sesión");

		return "login/iniciar-sesion";
	}

	@PostMapping("/input")
	public String procesar(Model modelo, @RequestParam String correo, @RequestParam String password)  {
		Usuario usuario = new Usuario();

		for (Usuario u : usuarioDao.getAll()) {
			if(u.getEmail().equals(correo)) {
				if(u.getPassword().equals(password)) {
					 usuario = u;
				}
			}
		}

		modelo.addAttribute("usuario", usuario);
		modelo.addAttribute("correo", correo);
		modelo.addAttribute("password", password);

		modelo.addAttribute("productos",productoDAO.getAll());



		return "comprador/comprador";
	}

}
