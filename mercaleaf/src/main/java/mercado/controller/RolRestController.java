package mercado.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mercado.model.dao.rol.IRolDAO;
import mercado.model.entity.Rol;

@RestController
@CrossOrigin(origins = { "http://localhost:4200" })
@RequestMapping("/api")
public class RolRestController {

	@Autowired
	private IRolDAO dao;

	@GetMapping("/roles")
	public List<Rol> getRoles() {

		return dao.getAll();
	}

	@GetMapping("/rol/{id}")
	public Rol show(@PathVariable Long id) {
		return dao.get(id);
	}
}
