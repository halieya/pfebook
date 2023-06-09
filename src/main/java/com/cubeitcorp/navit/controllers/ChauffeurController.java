package com.cubeitcorp.navit.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cubeitcorp.navit.dto.ChaufeurRequest;
import com.cubeitcorp.navit.enums.FonctionChauffeur;
import com.cubeitcorp.navit.models.Chauffeur;
import com.cubeitcorp.navit.repositories.ChauffeurRepository;

@RequestMapping("/api/v1")
@RestController
@CrossOrigin("*")
public class ChauffeurController {
	
	@Autowired
	private ChauffeurRepository chauffeurRepository;
	
	
	
	
	@PutMapping("/choff/update-one")
	@ResponseBody
	public ResponseEntity<?> updateOne(@RequestBody Chauffeur c){
		
		
		
		
		Chauffeur c_ = chauffeurRepository.findById(c.getId()).get();
		c_.setCin(c.getCin());
		c_.setEmail(c.getEmail());
		c_.setFilter(c.getFilter());
		c_.setFullname(c.getFullname());
		
		System.out.print(c.getCin());
		
		c_.setPermis(c.getPermis());
		c_.setTele(c.getTele());
		c_.setFonction(c.getFonction());
		c_.setScore_avg(0);
		
		
		return ResponseEntity.ok().body(chauffeurRepository.save(c_));
		
	
	}
	
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
	
	@GetMapping("/choff/get-one/by-id/by-filter/{id}")
	@ResponseBody
	public ResponseEntity<?> getchauffeurById(@PathVariable("id")String id ){
		
		Chauffeur ch = chauffeurRepository.findById(id).get();
		return ResponseEntity.ok().body(ch);
		
		
		
	}
	

	@DeleteMapping("/chauuff/delete-one/{id}")
	@ResponseBody
	public ResponseEntity<?> deleteOne( @PathVariable("id")  String id) {
		chauffeurRepository.deleteById(id);
		ResponseVehicle v = new ResponseVehicle("data " +  id   + "deleted");
		return ResponseEntity.ok().body(v);
		
		
	}
	
	
	
	@PostMapping("/create-chauffeur")

	public ResponseEntity<?> createOne(@RequestBody ChaufeurRequest c){
		
		Chauffeur c_ = new Chauffeur();
		c_.setCin(c.getCin());
		c_.setEmail(c.getEmail());
		c_.setFilter(c.getFilter());
		c_.setFullname(c.getFullname());
		c_.setFonction(c.getFonction());
		
		System.out.print(c.getCin());
		
		c_.setPermis(c.getPermis());
		c_.setTele(c.getTele());
	
		c_.setScore_avg(0);
		
		
		return ResponseEntity.ok().body(chauffeurRepository.save(c_));
	}
	
	

}
