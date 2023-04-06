package mercado.model.dao.rol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mercado.commons.GenericDAOImpl;
import mercado.model.entity.Rol;

@Repository("RolDaoImpl")
public class RolDaoImpl extends GenericDAOImpl<Rol, Long> implements IRolDAO {
	
	@Autowired
	private IRolCRUD crud;

	@Override
	public CrudRepository<Rol, Long> getDAO() {
		
		return crud;
	}
}
