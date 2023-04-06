package mercado.controller.PedidoApiRest;

import mercado.model.dao.comprador.ICompradorDAO;
import mercado.model.dao.estado.IEstadoDAO;
import mercado.model.dao.pedido.IPedidoDAO;
import mercado.model.dao.productor.IProductorDAO;
import mercado.model.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mercapp/compradores/crearPedido")
public class CrearPedido {

    @Autowired
    IPedidoDAO daoPedido;

    @Autowired
    IEstadoDAO daoEstado;

    @Autowired
    IProductorDAO daoProductor;

    @Autowired
    ICompradorDAO daoComprador;


    @PutMapping("/crearPedido/estado/{idEstado}/productor/{idProductor}/comprador/{idComprador}")
    public Pedido crearPedido(@PathVariable Long idEstado, @PathVariable Long idProductor, @PathVariable Long idComprador, @RequestBody List<DetallePedido> filasPedido) {

        Estado estado = daoEstado.get(idEstado);

        Pedido pedido = new Pedido();
        pedido.setEstado(estado);
        estado.setPedidos(pedido);
        pedido.setDetallePedido(filasPedido);


        Comprador comprador = daoComprador.get(idComprador);
        PedidoUsuario pedidoComprador = new PedidoUsuario(comprador, pedido);
        comprador.setPedidoUsuario(pedidoComprador);
        pedido.setPedidoUsuario(pedidoComprador);

        Productor productor = daoProductor.get(idProductor);
        PedidoUsuario pedidoProductor = new PedidoUsuario(productor, pedido);
        productor.setPedidoUsuario(pedidoProductor);
        pedido.setPedidoUsuario(pedidoProductor);

        return daoPedido.save(pedido);
    }

    @GetMapping("/holaMundo")
    public String holaMundoTest() {
        return "Hola mundo test";
    }

    @GetMapping("/jsonHola")
    public Hola json() {
        var hola = new Hola("Saludos", "Hola mundo test!");

        System.out.println("SERVER: [" + hola.getValor() + ", " + hola.getTitulo() + " ]");
        return hola;
    }

    @PostMapping("/holaMundoPOST")
    public ResponseEntity<Hola> holaMundoPOST(@RequestBody Hola hola) {
        System.out.println("SERVIDOR: Hola creado { " + hola.getTitulo() + ", " + hola.getValor() + "}");
        return new ResponseEntity<>(hola, HttpStatus.CREATED);
    }

    @PostMapping("/holaMundoPostUno")
    public Hola holaMundoPostUno(@RequestBody Hola hola) {
        System.out.println("SERVIDOR: Hola creado { " + hola.getTitulo() + ", " + hola.getValor() + "}");
        return hola;
    }

}
