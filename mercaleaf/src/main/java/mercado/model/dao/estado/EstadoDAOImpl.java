package mercado.model.dao.estado;

import mercado.commons.GenericDAOImpl;
import mercado.model.entity.Estado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("EstadoDaoImpl-1")
public class EstadoDAOImpl extends GenericDAOImpl<Estado, Long> implements IEstadoDAO {

    @Autowired
    private IEstadoCRUD crud;

    @Override
    public CrudRepository<Estado, Long> getDAO() {
        return crud;
    }
}