package com.cubeitcorp.navit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cubeitcorp.navit.dto.VehiculeReqUpdate;
import com.cubeitcorp.navit.models.Chauffeur;
import com.cubeitcorp.navit.models.Vehicle;
import com.cubeitcorp.navit.repositories.VehicleRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
class ResponseVehicle {
	String message;
}


@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class VehicleController {
	
	
	@Autowired 
	private VehicleRepository vehiculerepo;
	
	

	@GetMapping("/vehicle/get-one/by-id/by-filter/{id}")
	@ResponseBody
	public ResponseEntity<?> getchauffeurById(@PathVariable("id")String id ){
		
		Vehicle ch = vehiculerepo.findById(id).get();
		return ResponseEntity.ok().body(ch);
		
		
		
	}
	
	
	
	@DeleteMapping("/delete-one/{id}")
	@ResponseBody
	public ResponseEntity<?> deleteOne( @PathVariable("id")  String id) {
		vehiculerepo.deleteById(id);
		ResponseVehicle v = new ResponseVehicle("data " +  id   + "deleted");
		return ResponseEntity.ok().body(v);
		
		
	}
	
	
	@PutMapping("/update-one")
	@ResponseBody
	public ResponseEntity<?> updateOne(@RequestBody VehiculeReqUpdate v) {
Vehicle v1 = vehiculerepo.findById(v.getId()).get();

v1.setId(v.getId());
		
		v1.setCode(v.getCode());
		v1.setFilter(v.getFilter());
		v1.setMarque(v.getMarque());
		v1.setMatricule(v.getMatricule());
		v1.setType(v.getType());
		System.out.print(v1.getCode());
		
		Vehicle o =  vehiculerepo.save(v1);
		
		System.out.print(o.getCode());
		
		return ResponseEntity.ok().body( o);
	}
	
	
	

}
