package mercado.model.dao.pedido;

import mercado.model.entity.Pedido;
import org.springframework.data.repository.CrudRepository;

public interface IPedidoCRUD extends CrudRepository<Pedido, Long> {
}
