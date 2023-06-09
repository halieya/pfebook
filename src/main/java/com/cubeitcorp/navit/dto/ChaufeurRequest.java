package com.cubeitcorp.navit.dto;

import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;

import com.cubeitcorp.navit.enums.FonctionChauffeur;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChaufeurRequest {
	
private String filter;
	
	private String fullname;
	

	private String cin;

	private String email;
		
	private String permis;
		
	private String tele;
	
	private FonctionChauffeur fonction;

}
