package mercado.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import mercado.model.dao.producto.IProductoDAO;
import mercado.model.dao.productor.IProductorDAO;
import mercado.model.entity.Producto;
import mercado.model.entity.Productor;

@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping("/api")
public class ProductoRestController {

	@Autowired
	private IProductoDAO dao;

	@Autowired
	private IProductorDAO pdao;

	@GetMapping("/productos/{id}")
	public Producto getProduct(@PathVariable("id") Long id) {
		
		System.out.println("CALL API");
		Producto producto = dao.get(id);

		System.out.println("[ " + producto.getNombre() + " ] id : "+id);
		return producto;
	}

	@GetMapping("/productos")
	public List<Producto> getProductos() {
		return dao.getAll();
	}

	@PostMapping("/productos/{idUsuario}")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto create(@RequestBody Producto producto, @PathVariable("idUsuario") Long id) {

		Productor usuario = pdao.get(id);

		producto.setProductor(usuario);

		return dao.save(producto);
	}
}
