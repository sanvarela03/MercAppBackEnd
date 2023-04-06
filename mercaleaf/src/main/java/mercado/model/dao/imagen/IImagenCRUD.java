package mercado.model.dao.imagen;

import org.springframework.data.repository.CrudRepository;

import mercado.model.entity.ImageModel;

public interface IImagenCRUD extends CrudRepository<ImageModel, Long> {

}
