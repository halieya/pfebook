package com.cubeitcorp.navit.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cubeitcorp.navit.enums.FonctionChauffeur;
import com.cubeitcorp.navit.models.Chauffeur;


public interface ChauffeurRepository extends MongoRepository<Chauffeur,String>{

	List<Chauffeur> findByFilter(String filter);

	List<Chauffeur> findByFonction(FonctionChauffeur function);

	Chauffeur findByIdAndFilter(String id, String filter);
	
}
