package com.cubeitcorp.navit.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.cubeitcorp.navit.models.Notif;
import com.cubeitcorp.navit.repositories.NotifRepository;

@RestController
@RequestMapping("/cubeit/api/notifs")
@CrossOrigin("*")
public class NotificationRestController {
	
	@Autowired
	private NotifRepository notifRepository;
	
	
	@GetMapping("/get-all")
	@ResponseBody
	public List <Notif> getAllMissions (){
		return notifRepository.findAll();
	}
	
	@GetMapping("/get-all/by-filter/{filter}")
	@ResponseBody
	public List<Notif> getNotifsByFilter(@PathVariable("filter")String filter){
		
		return notifRepository.findByFilterOrderByTimestampDesc(filter);
		
	}
}
