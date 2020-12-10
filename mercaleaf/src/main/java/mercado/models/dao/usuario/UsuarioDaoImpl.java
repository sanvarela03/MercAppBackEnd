package mercado.models.dao.usuario;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mercado.commons.GenericDAOImpl;
import mercado.models.entity.Productor;
import mercado.models.entity.Transportador;
import mercado.models.entity.Usuario;

@Repository("UsuarioDaoImpl-1")
public class UsuarioDaoImpl extends GenericDAOImpl<Usuario, Long> implements IUsuarioDAO {

	@Autowired
	protected IUsuarioCRUD crud;

	@Override
	public CrudRepository<Usuario, Long> getDAO() {

		return crud;

	}

}
