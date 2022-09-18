package pe.edu.upc.serviceinterfaces;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entities.Torta;

public interface ITortaService {

	public Integer insert(Torta torta);
	
	List<Torta> list();
	
	public void delete(int idTorta);
	
	Optional<Torta> listId(int idTorta);
}
