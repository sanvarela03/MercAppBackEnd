package mercado.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class CompradorController {

    @RequestMapping("")
    public String mostrarProductos(){

        return "comprador/comprador";
    }
}
