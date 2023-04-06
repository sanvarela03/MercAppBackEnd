package mercado.model.dao.producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mercado.commons.GenericDAOImpl;
import mercado.model.entity.Producto;

@Repository("ProductoDaoImpl-1")
public class ProductoDaoImpl extends GenericDAOImpl<Producto, Long> implements IProductoDAO {

	@Autowired
	private IProductoCRUD crud;

	@Override
	public CrudRepository<Producto, Long> getDAO() {

		return crud;
	}

}
