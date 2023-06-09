package com.cubeitcorp.navit.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cubeitcorp.navit.models.Vehicle;
import com.cubeitcorp.navit.repositories.VehicleRepository;


@RestController
@RequestMapping("/cubeit/api/vehicles")
@CrossOrigin("*")
public class VehicleRestController {

	@Autowired
	private VehicleRepository vehicleRepository;
	
	
	@GetMapping("/get-all")
	@ResponseBody
	// @PreAuthorize("hasRole('USER')")
	public List<Vehicle> getAllVehicles(){

		return vehicleRepository.findAll();
	}
	
	
	@GetMapping("/get-all/by-filter/{filter}")
	@ResponseBody
	public List<Vehicle> getVehiclesByFilter(@PathVariable("filter")String filter){
		
		List<Vehicle> vehicles=vehicleRepository.findByFilter(filter);
		
		return vehicles;
	}
	

}
