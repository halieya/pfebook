package com.cubeitcorp.navit.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cubeitcorp.navit.enums.FonctionChauffeur;
import com.cubeitcorp.navit.models.Chauffeur;
import com.cubeitcorp.navit.models.Vehicle;
import com.cubeitcorp.navit.repositories.ChauffeurRepository;
import com.cubeitcorp.navit.repositories.VehicleRepository;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class ChaffeuVehiculeController {
	
	@Autowired
	private VehicleRepository vehicleRepository;
	
	@Autowired
	private ChauffeurRepository chRepository;
	
	
	@GetMapping("/vehi/allId")
	public ResponseEntity<?> geAlllVehiId()
	{
		
		List<String> ids = new ArrayList<>();
		
		List<Vehicle> vh = vehicleRepository.findAll();
		
		vh.stream().map(el->
			
			ids.add(el.getId())
			
			
		);
		
		
		return ResponseEntity.ok().body(ids);
		
		
		
	}

	@GetMapping("/conv/allId")
	public ResponseEntity<?> geAlllconvId()
	{
		
		List<String> ids = new ArrayList<>();
		
		List<Chauffeur> vh = chRepository.findAll();
		
		
		for (Chauffeur fc : vh) {
			
			if(fc.getFonction().equals(FonctionChauffeur.CONVOYEUR)) {
				ids.add(fc.getId());
			}
		}
		
		
		return ResponseEntity.ok().body(ids);
		
		
		
	}
	
	
	@GetMapping("/chau/allId")
	public ResponseEntity<?> geAlllchauId()
	{
		
		List<String> ids = new ArrayList<>();
		
		List<Chauffeur> vh = chRepository.findAll();
		
		
		for (Chauffeur fc : vh) {
			
			if(fc.getFonction().equals(FonctionChauffeur.CHAUFFEUR)) {
				ids.add(fc.getId());
			}
		}
		
		
		return ResponseEntity.ok().body(ids);
		
		
		
	}

}
