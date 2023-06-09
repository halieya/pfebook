package com.cubeitcorp.navit.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cubeitcorp.navit.models.Device;
import com.cubeitcorp.navit.repositories.DeviceRepository;
import com.cubeitcorp.navit.services.impl.NotifAlgorithm;
import com.cubeitcorp.navit.utils.TimestampUtils;

@RestController
@RequestMapping("/device/send")
@CrossOrigin("*")
public class DeviceRestController {
	
	@Autowired
	private DeviceRepository deviceRepository;
	
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	@Autowired
	private NotifAlgorithm notifAlgorithm;
	
	@PostMapping("/create-one")
	@ResponseBody
	public Device createOne(@RequestBody Device d) {
		d.setTimestamp(TimestampUtils.computeISO8601Timestamp());
		notifAlgorithm.scanAndControl(d);
		messagingTemplate.convertAndSend("/topic/device/tracking/"+d.getCode(), d);
		deviceRepository.save(d);
		return d;
	}

}
