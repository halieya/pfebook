package com.cubeitcorp.navit.models;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.cubeitcorp.navit.enums.FonctionChauffeur;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "chauffeur") 
@Data @NoArgsConstructor @AllArgsConstructor 
public class Chauffeur {

	@Id
	private String id;
	
	private String filter;
	
	private String fullname;
	
	 @Indexed(unique = true , direction = IndexDirection.DESCENDING)
	private String cin;
	 
	@Indexed(unique = true , direction = IndexDirection.DESCENDING)
	private String email;
		
	private String permis;
		
	private String tele;
		
	@Field("fonction")
	@Enumerated(EnumType.STRING)
	private FonctionChauffeur fonction;
	
	private int score_avg;
	
   
}
