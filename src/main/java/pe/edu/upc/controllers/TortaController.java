package pe.edu.upc.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.upc.entities.Torta;
import pe.edu.upc.serviceinterfaces.ITortaService;

@Controller
@RequestMapping("/tortas")
public class TortaController {
	
	@Autowired
	private ITortaService tS;
	
	@GetMapping("/new")
	public String newTorta(Model model) {
		model.addAttribute("torta", new Torta());
		return "torta/torta";
	}
	
	@GetMapping("/list")
	public String listDistritos(Model model) {
		try {
			model.addAttribute("torta", new Torta());
			model.addAttribute("listaTortas", tS.list());
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("error", e.getMessage());
		}
		return "torta/listTortas";
	}
	
	@PostMapping("/save")
	public String saveTortas(@Valid Torta torta, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("torta", torta);
			return "torta/torta";
		} else {
			int rpta = tS.insert(torta);
			if(rpta > 0) {
				model.addAttribute("torta", torta);
				model.addAttribute("mensaje", "Ya existe una torta con el mismo nombre y diametro");
				return "torta/torta";
			} else {
			model.addAttribute("mensaje", "Se registró correctamente");
			status.setComplete();
			}
		}
		model.addAttribute("torta", new Torta());
		return "redirect:/tortas/list";
	}

}
