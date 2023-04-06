package mercado.controller.ProductorApiRest;

import mercado.model.dao.productor.IProductorDAO;
import mercado.model.entity.Productor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mercapp/productores/consultarProductor")
public class ConsultarProductor {
    @Autowired
    private IProductorDAO dao;

    @GetMapping("/obtener/{idProductor}")
    public Productor obtenerPorId(@PathVariable("idProductor") Long idProductor) {

        Productor productor = dao.get(idProductor);

        if (productor == null) {
            System.out.println("No es un productor, verifique el id");
        }
        return productor;
    }

    @GetMapping("/obtener/todos")
    public List<Productor> obtenerTodos() {

        List<Productor> productores = dao.getAll();

        if (productores == null) {
            System.out.println("No se han encontrado productores");
        }
        return productores;
    }
}
