package com.cubeitcorp.navit.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cubeitcorp.navit.enums.StatusMission;
import com.cubeitcorp.navit.models.Chauffeur;
import com.cubeitcorp.navit.models.Mission;



public interface MissionRepository extends MongoRepository<Mission,String>{

	 List<Mission> findByFilter(String filter);
	 Mission findByIdAndFilter(String id, String filter);
	 Mission findByCode(String code);
	 
	 List<Mission> findByChauffeurAndStatus(Chauffeur chauffeur, StatusMission status);
}
