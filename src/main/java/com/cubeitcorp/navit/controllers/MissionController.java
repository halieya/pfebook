package com.cubeitcorp.navit.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cubeitcorp.navit.enums.StatusMission;
import com.cubeitcorp.navit.models.Chauffeur;
import com.cubeitcorp.navit.models.Mission;
import com.cubeitcorp.navit.models.Vehicle;
import com.cubeitcorp.navit.repositories.ChauffeurRepository;
import com.cubeitcorp.navit.repositories.MissionRepository;
import com.cubeitcorp.navit.repositories.VehicleRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
class MiisionRes{
	String missionres;
}

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class MissionController {
	
	@Autowired
	private MissionRepository missionrepo;
	
	@GetMapping("/miss/get-all")
	@ResponseBody
	public List <Mission> getAllMissions (){
		return missionrepo.findAll();
	}
	
	
	@GetMapping("/miss/{id}")
	public ResponseEntity<?> getByidmision(@PathVariable("id") String id )
	{
		Mission mis = missionrepo.findById(id).get();
		
		return ResponseEntity.ok().body(mis);
		
		
		
	}
	
	@GetMapping("/miss/get-all/by-filter/{filter}")
	@ResponseBody
	public List<Mission> getMissionsByFilter(@PathVariable("filter")String filter){
		
		return missionrepo.findByFilter(filter);
		
	}
	
	
	
	
	
	
	
	@PutMapping("/mission-in-progress")
	@ResponseBody
	public ResponseEntity<Mission> updateInProgress(@PathVariable("id") String id) {
		
		Mission mission = missionRepository.findById(id).get();
		
			if (mission.getStatus() == StatusMission.FINISHED) {
				
				return new ResponseEntity<>(mission, HttpStatus.BAD_REQUEST);
			}else {
				mission.setStatus(StatusMission.INPROGRESS);
				missionRepository.save(mission);
				return new ResponseEntity<>(mission, HttpStatus.ACCEPTED);
			}
		
	}
	
	
	
	
	
	
	
	@GetMapping("/miss/get-one/by-id/by-filter/{id}/{filter}")
	@ResponseBody
	public Mission getSpecificMissionDetails(@PathVariable("id")String id ,@PathVariable("filter")String filter){
		
		return missionrepo.findByIdAndFilter(id,filter);
		
		
		
	}
	
	
	
	
	@Autowired
	private MissionRepository missionRepository; 

	@Autowired
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private VehicleRepository vehicleRepository;
	
	@Autowired
	private ChauffeurRepository chauffeurRepository;
//creation Mission with DBREF
@PostMapping("/miss/create-mission-with-DBREF")

@ResponseBody
public Mission createOne(@RequestBody Mission m,@RequestParam String id_vehicle,
		                 @RequestParam String id_ch ,
		                 @RequestParam String id_conv ) {
	
		Vehicle v = vehicleRepository.findById(id_vehicle).get();
		Chauffeur ch = chauffeurRepository.findById(id_ch).get();
		Chauffeur convoyeur= chauffeurRepository.findById(id_conv).get();
		
		m.setStatus(m.getStatus());
		m.setFilter("BE987654TN123");
		m.setChauffeur(ch);
		m.setConvoyeur(convoyeur);
		m.setVehicle(v);
		m.setDateDep(new Date());
		
		if(m.getStatus().equals(StatusMission.FINISHED))
			m.setDateAr(new Date());
		missionRepository.save(m);
		return m;
	}


//update Mission with DBREF
@PutMapping("/miss/update-mission-with-DBREF")

@ResponseBody
public Mission updateOne(@RequestBody Mission m,@RequestParam String id_vehicle,
		                 @RequestParam String id_ch ,
		                 @RequestParam String id_conv ) {
	
		Vehicle v = vehicleRepository.findById(id_vehicle).get();
		Chauffeur ch = chauffeurRepository.findById(id_ch).get();
		Chauffeur convoyeur= chauffeurRepository.findById(id_conv).get();
		
		m.setFilter("BE987654TN123");
	  m.setStatus(m.getStatus());
		m.setChauffeur(ch);
		m.setConvoyeur(convoyeur);
		m.setVehicle(v);
		if(m.getStatus().equals(StatusMission.FINISHED))
		m.setDateAr(new Date());
		
	missionrepo.save(m);
		return m;
	}


//API Delete mission By ID
@DeleteMapping("/miss/delete-one/{id}")
@ResponseBody

public ResponseEntity<?> deleteMissionById(@PathVariable ("id")String id) {
	
	
	//Mission m_ = missionrepo.findById(id).get();
		
	missionrepo.deleteById(id);
	
	return ResponseEntity.ok().body(new MiisionRes("deleted"));
	
	}
	

}
