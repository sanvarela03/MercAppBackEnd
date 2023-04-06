package mercado.model.dao.productor;

import org.springframework.data.repository.CrudRepository;

import mercado.model.entity.Productor;

public interface IProductorCRUD extends CrudRepository<Productor, Long> {

}
