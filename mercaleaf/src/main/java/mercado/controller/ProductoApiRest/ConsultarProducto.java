package mercado.controller.ProductoApiRest;

import mercado.model.dao.producto.IProductoDAO;
import mercado.model.entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/mercapp/productores/consultarProducto")
public class ConsultarProducto {

    @Autowired
    private IProductoDAO daoProducto;

    @GetMapping("/obtenerTodos/{idProductor}")
    public List<Producto> obtenerTodosPorId(@PathVariable("idProductor") Long idProductor) {
        List<Producto> productos = daoProducto.getAll();
        return filtrarProductos(idProductor, productos);
    }

    @GetMapping("/obtenerTodos")
    public List<Producto> obtenerTodos() {
        List<Producto> productos = daoProducto.getAll();
        return productos;
    }

    private List<Producto> filtrarProductos(Long idProductor, List<Producto> productos) {
        List<Producto> productosDelProductor = new ArrayList();

        if (productos != null) {
            for (Producto p : productos) {
                if (idProductor == p.getProductor().getIdUsuario()) {
                    productosDelProductor.add(p);
                }
            }
        } else {
            System.out.println("No hay productos");
        }
        return productosDelProductor;
    }
}
