package mercado.commons;

import org.springframework.stereotype.Component;


import java.io.InputStream;

//@Component
public class CargarImagen {
    private InputStream inputStream;

    public CargarImagen(){
        inputStream = this.getClass().getClassLoader().getResourceAsStream("usuario-img.jpg");
    }

    public InputStream getInputStream() {
        return inputStream;
    }
}
