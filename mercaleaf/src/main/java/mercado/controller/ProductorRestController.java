package mercado.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import mercado.model.dao.productor.IProductorDAO;
import mercado.model.entity.Productor;

@RestController
@CrossOrigin(origins = { "http://localhost:4200" })
@RequestMapping("/mercaleaf/productores")
public class ProductorRestController {

	@Autowired
	private IProductorDAO dao;

	@DeleteMapping("/borrar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		dao.delete(id);
	}

	@GetMapping("/obtener/todos")
	public List<Productor> getProductores() {
		return dao.getAll();
	}

	@PostMapping("/guardar")
	public Productor guardarProductor(@RequestBody Productor productor) {

		String[] partes = productor.getNombre().split(" ");

		switch (partes.length) {
		case 1: {
			productor.setNombre(partes[0]);
		}
			break;

		case 2: {
			productor.setNombre(partes[0]);
			productor.setApellido(partes[1]);
		}
			break;

		case 3: {
			productor.setNombre(partes[0]);
			productor.setApellido(partes[1] + " " + partes[2]);
		}
			break;

		case 4: {
			productor.setNombre(partes[0] + " " + partes[1]);
			productor.setApellido(partes[2] + " " + partes[3]);
		}
			break;

		default: {
			System.out.println("No se pudo distribuir el nombre" + productor.getNombre());
		}
		}

		System.out.println(Arrays.toString(partes));

		return dao.save(productor);
	}

	@GetMapping("/obtener/{email}")
	public Productor getByEmail(@PathVariable("email") String email) {

		List<Productor> productores = dao.getAll();

		for (Productor productor : productores) {
			if (productor.getEmail().equals(email)) {
				return productor;
			}
		}

		return null;
	}

}
