package mercado.model.dao.imagen;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import mercado.model.entity.ImageModel;

public interface ImageRepository extends CrudRepository<ImageModel, Long> {

	public Optional<ImageModel> findByName(String name);

	@Query("from ImageModel where usuario.id = ?1")
	public List<ImageModel> encontrarPorUsuario(Long id);
}
