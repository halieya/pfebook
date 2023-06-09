

package com.cubeitcorp.navit.services.impl;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.cubeitcorp.navit.dto.VehicleDTO;
import com.cubeitcorp.navit.models.Device;
import com.cubeitcorp.navit.models.Mission;
import com.cubeitcorp.navit.models.Notif;
import com.cubeitcorp.navit.repositories.MissionRepository;
import com.cubeitcorp.navit.repositories.NotifRepository;
import com.cubeitcorp.navit.repositories.VehicleRepository;
import com.cubeitcorp.navit.utils.TimestampUtils;

import lombok.extern.log4j.Log4j2;


@Log4j2
@Service
public class NotifAlgorithm {
	
	@Autowired
	private NotifRepository notifRepository;
	
	@Autowired
	private VehicleRepository vehicleRepository;
	
	@Autowired
	private MissionRepository missionRepository;
	
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	private final Map<String, Integer> speedMap = new HashMap<>();
	private final Map<String, Integer> rpmMap = new HashMap<>();
	private final Map<String, Integer> fuelMap = new HashMap<>();
	private final Map<String, Integer> tempMap = new HashMap<>();
	private final Map<String, Integer> restMap = new HashMap<>();

	public void scanAndControl(Device d) {
		
		String deviceCode = d.getCode();
		VehicleDTO v = vehicleRepository.findFirstByCode(deviceCode);
		log.info(v);
		
		
				// Check engine RPM
				if (d.getEngine_rpm() > 3500) {
					handleCondition("rpm", deviceCode, 5, v, "Engine RPM", "High engine RPM detected on Vehicle : " + v.getMatricule(), rpmMap);
				} else {
					resetCounter("rpm", deviceCode, rpmMap);
				}

				// Check engine temperature
				if (d.getEngine_temperature() > 100) {
					handleCondition("temp", deviceCode, 5, v, "Engine temperature", "High engine temperature detected on Vehicle : " + v.getMatricule(), tempMap);
				} else {
					resetCounter("temp", deviceCode, tempMap);
				}

				// Check fuel level
				if (d.getFuel_level() < 10) {
					handleCondition("fuel", deviceCode, 5, v, "Fuel level ", "Low fuel level detected on Vehicle : " + v.getMatricule(), fuelMap);
				} else {
					resetCounter("fuel", deviceCode, fuelMap);
				}

				// Check effective speed
				if (d.getEffective_speed() > 120) {
					handleCondition("speed", deviceCode, 5, v, "Speed", "High speed detected on Vehicle : " + v.getMatricule(), speedMap);
				} else {
					resetCounter("speed", deviceCode, speedMap);
				}

				// Check rest
				if (d.getEngine_on_time().compareTo(LocalTime.of(2, 30)) >= 0) {
					handleCondition("rest", deviceCode, 5, v, "Pause", "Driver Needs to rest: " + v.getMatricule(), restMap);
				} else {
					resetCounter("rest", deviceCode, restMap);
				}
		
		
	}

	private void handleCondition(String key, String deviceCode, int threshold, VehicleDTO vehicle, String title, String desc, Map<String, Integer> map) {
		Integer count = map.get(deviceCode);
		if (count == null) {
			count = 0;
		}
		if (count == threshold - 1) {
			createNotification(vehicle.getFilter(), title, desc);
			Mission mission = missionRepository.findByCode(deviceCode);
			// JavaSMSHandler(NUMTELE , String title , String desk);
			// JavaSMSHandler(mission.getChauffeur().getTele() , "Pause" , "You Need To Rest ");
			updateMissionScore(mission);
			map.put(deviceCode, 0);
		} else {
			count++;
			map.put(deviceCode, count);
		}
	}
	
	private void resetCounter(String key, String deviceCode, Map<String, Integer> map) {
		map.remove(deviceCode);
	
	}

	private void updateMissionScore(Mission mission) {
		if (mission != null) 
		{
			int score = mission.getScore_chauffeur();
			if(score > 0) 
			{
				score--;
				mission.setScore_chauffeur(score);
				missionRepository.save(mission);
				log.info("Mission score updated to : " + score);
			}
		}
	}
	
	
	private void createNotification(String filter, String title, String desc) {
		// Create a new notification entity and save it to the database
		Notif notif = new Notif(filter, title, desc, TimestampUtils.computeISO8601Timestamp());
		notifRepository.save(notif);
		log.info(notif);

		// Send the notification to any subscribed clients via a WebSocket or similar mechanism
		// (implementation not shown)
		messagingTemplate.convertAndSend("/topic/notif/" + filter, notif);
	}
	

		
}
