package com.cubeitcorp.navit.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cubeitcorp.navit.dto.VehiculeRequest;
import com.cubeitcorp.navit.enums.StatusMission;
import com.cubeitcorp.navit.models.Chauffeur;
import com.cubeitcorp.navit.models.Mission;
import com.cubeitcorp.navit.models.Vehicle;
import com.cubeitcorp.navit.repositories.ChauffeurRepository;
import com.cubeitcorp.navit.repositories.MissionRepository;
import com.cubeitcorp.navit.repositories.VehicleRepository;
import com.cubeitcorp.navit.utils.TimestampUtils;

@RestController
@RequestMapping("/navit/main")
@CrossOrigin("*")
public class NavitMainController {

	@Autowired
	private MissionRepository missionRepository; 

	@Autowired
	private VehicleRepository vehicleRepository;
	
	@Autowired
	private ChauffeurRepository chauffeurRepository;
	
	
	

	//	API Create Vehicle
	@PostMapping("/create-vehicle")
	//@ResponseBody
	public ResponseEntity<?> createOne( @RequestBody VehiculeRequest v){
		
		
		Vehicle v1 = new Vehicle();
		
		v1.setCode(v.getCode());
		v1.setFilter(v.getFilter());
		v1.setMarque(v.getMarque());
		v1.setMatricule(v.getMatricule());
		v1.setType(v.getType());
		System.out.print(v1.getCode());
		
		Vehicle o =  vehicleRepository.save(v1);
		
		System.out.print(o.getCode());
		
		return ResponseEntity.ok().body( vehicleRepository.save(o));
		
		
	}
	
	
	//	API Create Chauffeur
	@PostMapping("/create-chauffeur")
	@ResponseBody
	public Chauffeur createOne(@RequestBody Chauffeur c){
		
		c.setScore_avg(0);
		chauffeurRepository.save(c);
		
		return c;
	}
	
	
	
	// ################ MISSION DAO ##################
	@PostMapping("/create-mission-with-DBREF")
	@ResponseBody
	public Mission createOne(@RequestBody Mission m,@RequestParam String id_vehicle,
			                 @RequestParam String id_ch ,
			                 @RequestParam String id_conv ) 
	{
		
			Vehicle v = vehicleRepository.findById(id_vehicle).get();
			Chauffeur ch = chauffeurRepository.findById(id_ch).get();
			Chauffeur convoyeur= chauffeurRepository.findById(id_conv).get();
			
			m.setDateAr(null);
			m.setCode(v.getCode());
			m.setStatus(StatusMission.STANDBY);
			m.setScore_chauffeur(20);
			m.setChauffeur(ch);
			m.setConvoyeur(convoyeur);
			m.setVehicle(v);
			
			missionRepository.save(m);
			return m;
		}
	
	
	@PutMapping("/finish-mission-code-reset")
	@ResponseBody
	public Mission updateStatusAndCode(@RequestParam String id) {
		Mission mission = missionRepository.findById(id).get();
		
			Date date = new Date();
			mission.setDateAr(date);
		  
			mission.setStatus(StatusMission.FINISHED);
			mission.setCode(TimestampUtils.computeISO8601Timestamp());
		
		
		
		missionRepository.save(mission);
		
		return mission;
	}

	@PutMapping("/mission-in-progress")
	@ResponseBody
	public ResponseEntity<Mission> updateInProgress(@RequestParam String id) {
		
		Mission mission = missionRepository.findById(id).get();
		
			if (mission.getStatus() == StatusMission.FINISHED) {
				
				return new ResponseEntity<>(mission, HttpStatus.BAD_REQUEST);
			}else {
				mission.setStatus(StatusMission.INPROGRESS);
				missionRepository.save(mission);
				return new ResponseEntity<>(mission, HttpStatus.ACCEPTED);
			}
		
	}
	
	
}
