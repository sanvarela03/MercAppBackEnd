package mercado.controller.ProductorApiRest;

import mercado.model.dao.productor.IProductorDAO;
import mercado.model.entity.Productor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mercapp/productores/eliminarProductor")
public class EliminarProductor {

    @Autowired
    private IProductorDAO dao;


    @GetMapping("/eliminar/{idProductor}")
    public void eliminarPorId(@PathVariable("idProductor") Long idProductor) {

        Productor productor = dao.get(idProductor);

        if (productor == null) {
            System.out.println("No es un productor, verifique el id");
        }

        productor.setDisponibilidadDeConsulta(false);

        dao.save(productor);

    }
}
