package mercado.controller.PedidoApiRest;

public class Hola {
    private String titulo;
    private String valor;

    public Hola(String titulo, String valor) {
        this.titulo = titulo;
        this.valor = valor;
    }

    public Hola() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}