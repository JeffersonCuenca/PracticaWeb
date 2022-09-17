package pe.edu.upc.serviceinterfaces;

import java.util.List;

import pe.edu.upc.entities.Torta;

public interface ITortaService {

	public Integer insert(Torta torta);
	
	List<Torta> list();
}
