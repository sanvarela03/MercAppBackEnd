package mercado.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "automovil")
@Getter
@Setter
public class Automovil {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_automovil")
	private Long id;
	// ...
	private Long capacidadEnKilogramos;

	@OneToOne(mappedBy = "automovil", fetch = FetchType.LAZY)
	private Transportador transportador;

}