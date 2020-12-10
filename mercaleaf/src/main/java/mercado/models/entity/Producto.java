package mercado.models.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "producto")
public class Producto {

	@Id
	@Column(name = "id_producto")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

//	@JsonManagedReference
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_productor", referencedColumnName = "id_usuario")
	private Productor productor;
	
	
	@OneToMany(mappedBy = "producto")
	@JsonIgnore
	private List<DetallePedido> detallePedido;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	private Tipo tipo;

	private String nombre;

	private String descripcion;

	private String precio;

	private String costo;
	
	private String capacidadVenta;

	public String getCapacidadVenta() {
		return capacidadVenta;
	}

	public void setCapacidadVenta(String capacidadVenta) {
		this.capacidadVenta = capacidadVenta;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Productor getProductor() {
		return productor;
	}

	public void setProductor(Productor productor) {
		this.productor = productor;
	}

	public List<DetallePedido> getDetallePedido() {
		return detallePedido;
	}

	public void setDetallePedido(List<DetallePedido> detallePedido) {
		this.detallePedido = detallePedido;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	public String getCosto() {
		return costo;
	}

	public void setCosto(String costo) {
		this.costo = costo;
	}
}
