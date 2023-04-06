package mercado.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rutaVariable")
public class EjemploPathVariableController {
	
	@GetMapping("/string/{texto}")
	public String variables(@PathVariable String texto, Model modelo) {
		
		modelo.addAttribute("titulo", "Recibir Parametros del request HTTP GET - URL");
		
		return "variables/ver";
	}
	
	
}
	