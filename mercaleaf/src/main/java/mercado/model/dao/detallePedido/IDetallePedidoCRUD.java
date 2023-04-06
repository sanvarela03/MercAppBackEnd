package mercado.model.dao.detallePedido;

import mercado.model.entity.DetallePedido;
import org.springframework.data.repository.CrudRepository;

public interface IDetallePedidoCRUD  extends CrudRepository<DetallePedido, Long> {
}
