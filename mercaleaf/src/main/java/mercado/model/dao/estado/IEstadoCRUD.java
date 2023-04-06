package mercado.model.dao.estado;

import mercado.model.entity.Estado;
import org.springframework.data.repository.CrudRepository;

public interface IEstadoCRUD extends CrudRepository<Estado, Long> {
}
