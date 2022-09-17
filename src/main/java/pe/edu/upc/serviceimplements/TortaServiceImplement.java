package pe.edu.upc.serviceimplements;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entities.Torta;
import pe.edu.upc.repositories.ITortaRepository;
import pe.edu.upc.serviceinterfaces.ITortaService;

@Service
public class TortaServiceImplement implements ITortaService{

	@Autowired
	private ITortaRepository tR;

	@Override
	public Integer insert(Torta torta) {
		// TODO Auto-generated method stub
		int rpta = tR.TortaExistentes(torta.getNombreTorta(), torta.getDiametroTorta(), torta.getPorcionesTorta());
		if(rpta == 0) {
			tR.save(torta);
		}
		return rpta;
	}

	@Override
	public List<Torta> list() {
		// TODO Auto-generated method stub
		return tR.findAll();
	}
}
