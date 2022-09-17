package pe.edu.upc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Torta;

@Repository
public interface ITortaRepository extends JpaRepository<Torta, Integer>{
	
	@Query("select count(t.nombreTorta) "
	+ "from Torta t " 
	+ "where (t.nombreTorta=:name and t.diametroTorta=:diameter) or (t.nombreTorta=:name and t.porcionesTorta=:portions)")
	public int TortaExistentes(@Param("name") String nombre, @Param("diameter") Double diametro, @Param("portions") String porciones);
}
