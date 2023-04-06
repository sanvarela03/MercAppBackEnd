package mercado.model.dao.comprador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mercado.commons.GenericDAOImpl;
import mercado.model.entity.Comprador;

@Repository("CompradorDaoImpl-1")
public class CompradorDaoImpl extends GenericDAOImpl<Comprador, Long> implements ICompradorDAO {

	@Autowired
	private ICompradorCRUD crud;
	

	@Override
	public CrudRepository<Comprador, Long> getDAO() {

		return crud;
	}

}
