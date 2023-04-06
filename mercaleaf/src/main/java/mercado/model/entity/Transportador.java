package mercado.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "transportador")
public class Transportador extends Usuario {

	private static final long serialVersionUID = 1L;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_automovil", referencedColumnName = "id_automovil")
	private Automovil automovil;

	public Automovil getAutomovil() {
		return automovil;
	}

	public void setAutomovil(Automovil automovil) {
		this.automovil = automovil;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}