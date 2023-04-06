package mercado.controller.ProductorApiRest;

import mercado.model.dao.productor.IProductorDAO;
import mercado.model.dao.rol.IRolDAO;
import mercado.model.entity.Productor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mercapp/productores/actualizarProductor")
public class ActualizarProductor {
    @Autowired
    private IProductorDAO dao;
    @Autowired
    private IRolDAO daoRol;

    @PostMapping("/actualizar")
    public Productor actualizar(@RequestBody Productor productor) {

        System.out.println("PROCESANDO dirMem: [ " + productor.hashCode() + " ] Datos: " + productor.toString());

        return dao.save(productor);
    }

}
