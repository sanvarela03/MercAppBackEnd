package mercado.model.dao.detallePedido;

import mercado.commons.GenericDAOImpl;
import mercado.model.entity.DetallePedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("DetallePedidoDaoImpl-1")
public class DetallePedidoDAOImpl extends GenericDAOImpl<DetallePedido, Long> implements IDetallePedidoDAO {

    @Autowired
    private IDetallePedidoCRUD crud;


    @Override
    public CrudRepository<DetallePedido, Long> getDAO() {

        return crud;
    }

}
