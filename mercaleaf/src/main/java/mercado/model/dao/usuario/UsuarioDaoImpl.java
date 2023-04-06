package mercado.model.dao.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mercado.commons.GenericDAOImpl;
import mercado.model.entity.Usuario;

@Repository("UsuarioDaoImpl-1")
public class UsuarioDaoImpl extends GenericDAOImpl<Usuario, Long> implements IUsuarioDAO {

	@Autowired
	protected IUsuarioCRUD crud;

	@Override
	public CrudRepository<Usuario, Long> getDAO() {

		return crud;

	}

}
