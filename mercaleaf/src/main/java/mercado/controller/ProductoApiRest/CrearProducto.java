package mercado.controller.ProductoApiRest;

import mercado.model.dao.producto.IProductoDAO;
import mercado.model.dao.productor.IProductorDAO;
import mercado.model.entity.Producto;
import mercado.model.entity.Productor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mercapp/productores/crearProducto")
public class CrearProducto {

    @Autowired
    private IProductorDAO daoProductor;

    @Autowired
    private IProductoDAO daoProducto;

    @PostMapping("/guardar/{idProductor}")
    public Producto guardarProducto(@RequestBody Producto producto, @PathVariable("idProductor") Long idProductor) {

        if (producto == null) {
            System.out.println("No hay nada en el request body");
        } else {
            System.out.println(producto.toString());
            Productor productorABuscar = daoProductor.get(idProductor);

            if (productorABuscar != null) {
                producto.setProductor(productorABuscar);
                daoProducto.save(producto);
                System.out.println("Producto guardado y asociado al productor");
            } else {
                System.out.println("Verifique que el usuario sea un productor");
            }
        }
        return producto;
    }

}
