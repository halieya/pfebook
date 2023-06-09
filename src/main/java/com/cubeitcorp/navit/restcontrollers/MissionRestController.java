package com.cubeitcorp.navit.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cubeitcorp.navit.models.Mission;
import com.cubeitcorp.navit.repositories.MissionRepository;

@RestController
@RequestMapping("/cubeit/api/missions")
@CrossOrigin("*")
public class MissionRestController {

	@Autowired
	private MissionRepository missionRepository;
	
	@GetMapping("/get-all")
	@ResponseBody
	public List <Mission> getAllMissions (){
		return missionRepository.findAll();
	}
	
	
	@GetMapping("/get-all/by-filter/{filter}")
	@ResponseBody
	public List<Mission> getMissionsByFilter(@PathVariable("filter")String filter){
		
		return missionRepository.findByFilter(filter);
		
	}
	
	@GetMapping("/get-one/by-id/by-filter/{id}/{filter}")
	@ResponseBody
	public Mission getSpecificMissionDetails(@PathVariable("id")String id ,@PathVariable("filter")String filter){
		
		return missionRepository.findByIdAndFilter(id,filter);
		
		
		
	}
	
	
	
}
