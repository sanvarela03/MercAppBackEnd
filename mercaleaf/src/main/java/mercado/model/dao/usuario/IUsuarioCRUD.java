package mercado.model.dao.usuario;

import org.springframework.data.repository.CrudRepository;

import mercado.model.entity.Usuario;

public interface IUsuarioCRUD extends CrudRepository<Usuario, Long> {

}
