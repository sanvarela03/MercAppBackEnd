package mercado.model.dao.usuario;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import mercado.model.entity.Usuario;


public interface IUsuarioRepository extends  CrudRepository<Usuario, Long> {
	
	@Query("FROM Usuario WHERE email = ?1 AND rol.nombre ='productor'")
	public List<Usuario> encontrarUsuariosPorEmail(String email);
	
	
	
}
