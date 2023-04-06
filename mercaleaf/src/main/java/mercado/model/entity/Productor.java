package mercado.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "productor")
@Getter
@Setter
public class Productor extends Usuario {

	private static final long serialVersionUID = 1L;

//	@JsonBackReference
//	@JsonIgnore

	@OneToMany(mappedBy = "productor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Producto> productos;

	public Productor() {
		productos = new ArrayList<>();
	}

}
