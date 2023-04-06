package mercado.model.dao.producto;

import org.springframework.data.repository.CrudRepository;

import mercado.model.entity.Producto;

public interface IProductoCRUD extends CrudRepository<Producto, Long> {

}
