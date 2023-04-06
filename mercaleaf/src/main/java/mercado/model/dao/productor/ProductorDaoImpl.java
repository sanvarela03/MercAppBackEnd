package mercado.model.dao.productor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mercado.commons.GenericDAOImpl;
import mercado.model.entity.Productor;

@Repository("ProductorDaoImpl-1")
public class ProductorDaoImpl extends GenericDAOImpl<Productor, Long> implements IProductorDAO {

	@Autowired
	private IProductorCRUD crud;

	@Override
	public CrudRepository<Productor, Long> getDAO() {

		return crud;
	}

}
