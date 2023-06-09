package com.cubeitcorp.navit.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cubeitcorp.navit.models.Chauffeur;
import com.cubeitcorp.navit.repositories.ChauffeurRepository;

@RestController
@RequestMapping("/cubeit/api/chauffeurs")
@CrossOrigin("*")
public class ChauffeurRestController {

	@Autowired
	private ChauffeurRepository chauffeurRepository;
	
	
	@GetMapping("/get-all")
	@ResponseBody
	// @PreAuthorize("hasRole('USER')")
	public List<Chauffeur> getAllChauffeurs(){

		return chauffeurRepository.findAll();
	}
	
	@GetMapping("/get-all/by-filter/{filter}")
	@ResponseBody
	public List<Chauffeur> getChauffeursByFilter(@PathVariable("filter")String filter){
		
		return chauffeurRepository.findByFilter(filter);
		
	}
	
	@GetMapping("/get-one/by-id/by-filter/{id}/{filter}")
	@ResponseBody
	public Chauffeur getSpecificChauffeurDetails(@PathVariable("id")String id ,@PathVariable("filter")String filter){
		
		return chauffeurRepository.findByIdAndFilter(id,filter);
		
		
		
	}
	
	
}
