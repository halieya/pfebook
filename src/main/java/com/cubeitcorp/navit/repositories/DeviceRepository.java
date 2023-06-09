package com.cubeitcorp.navit.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

import com.cubeitcorp.navit.models.Device;



public interface DeviceRepository extends MongoRepository<Device,String>{

	@Async
	@Transactional
	Device findTopByCodeOrderByTimestampDesc(String code);


	

}
