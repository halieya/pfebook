package com.cubeitcorp.navit.services.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cubeitcorp.navit.enums.FonctionChauffeur;
import com.cubeitcorp.navit.enums.StatusMission;
import com.cubeitcorp.navit.models.Chauffeur;
import com.cubeitcorp.navit.models.Mission;
import com.cubeitcorp.navit.repositories.ChauffeurRepository;
import com.cubeitcorp.navit.repositories.MissionRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ChauffeurAVGScoreAlgorithm {

	@Autowired
	private MissionRepository missionRepository;
	
	@Autowired
	private ChauffeurRepository chauffeurRepository;
	
	@Transactional
	//@Scheduled(fixedRate = 30000)
	public void setAVGSCOREChauffeur() throws NoSuchElementException, InterruptedException {
		
		final List<Chauffeur> chauffeurs = chauffeurRepository.findByFonction(FonctionChauffeur.CHAUFFEUR);
		
		for (Chauffeur c : chauffeurs) {
			
			final List<Mission> missions = missionRepository.findByChauffeurAndStatus(c , StatusMission.FINISHED);
				
			  	int totalScore = 0;
		        for (Mission mission : missions) {
		            totalScore += mission.getScore_chauffeur();
//		            log.info("totalScore :  " + totalScore);
//		            log.info("size :  " + missions.size());
		        }
		        int scoreAvg = (int) Math.round((double) totalScore / (missions.size()) );
		        c.setScore_avg(scoreAvg);
		        chauffeurRepository.save(c);
		    	log.info("scoreAvg :  " + scoreAvg);
		    	
		    	missions.clear();
		}
		
	
		chauffeurs.clear();
		
		
	}
	
	
	
}
