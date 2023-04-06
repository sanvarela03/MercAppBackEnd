package mercado.controller.ProductorApiRest;

import mercado.model.dao.productor.IProductorDAO;
import mercado.model.entity.Productor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/mercapp/productores/crearProductor")
public class CrearProductor {
    @Autowired
    private IProductorDAO dao;

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
}
