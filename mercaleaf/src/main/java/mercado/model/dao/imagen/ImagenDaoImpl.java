package mercado.model.dao.imagen;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mercado.commons.GenericDAOImpl;

import mercado.model.entity.ImageModel;

@Repository("ImagenDaoImpl")
public class ImagenDaoImpl extends GenericDAOImpl<ImageModel, Long> implements IImagenDAO {

	@Autowired
	private IImagenCRUD crud;

	@Override
	public CrudRepository<ImageModel, Long> getDAO() {

		return crud;
	}
}
