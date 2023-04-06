package mercado.model.dao.pedido;

import mercado.commons.GenericDAOImpl;
import mercado.model.entity.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("PedidoDaoImpl-1")
public class PedidoDAOImpl  extends GenericDAOImpl<Pedido, Long> implements IPedidoDAO {

    @Autowired
    private IPedidoCRUD crud;

    @Override
    public CrudRepository<Pedido, Long> getDAO() {

        return crud;
    }

}
