package mercado.model.dao.transportador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mercado.commons.GenericDAOImpl;
import mercado.model.entity.Transportador;

@Repository("TransportadorDaoImpl-1")
public class TransportadorDaoImpl extends GenericDAOImpl<Transportador, Long> implements ITransportadorDAO {

	@Autowired
	private ITransportadorCRUD crud;

	@Override
	public CrudRepository<Transportador, Long> getDAO() {

		return crud;
	}

}
