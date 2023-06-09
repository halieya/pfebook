package com.cubeitcorp.navit.models;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import com.cubeitcorp.navit.enums.StatusMission;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Document(collection = "mission") 
@Data @NoArgsConstructor @AllArgsConstructor 
public class Mission {

	@Id
	private String id;
	
	private String filter;
	
	@Field("status")
	@Enumerated(EnumType.STRING)
	private StatusMission status;
	
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH mm")
	private Date dateDep;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH mm")
	private Date dateAr;
	

	private String cargo;
	
	private int weight;
	
	@DBRef
	private Vehicle vehicle;
	
	@DBRef
	private Chauffeur chauffeur;
	
	@DBRef
	private Chauffeur convoyeur;
	
    @Indexed(unique = true , direction = IndexDirection.DESCENDING)
	private String code;
	
    private int score_chauffeur;
	
	
}
