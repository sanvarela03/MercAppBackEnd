package mercado.model.dao.comprador;

import org.springframework.data.repository.CrudRepository;

import mercado.model.entity.Comprador;

public interface ICompradorCRUD extends CrudRepository<Comprador, Long> {

}
