package com.cubeitcorp.navit.dto;

import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehiculeRequest {
	
	
	
	private String code;

  
	private String matricule;

	private String marque;
	
	private String type;
	
	private String filter;

}
