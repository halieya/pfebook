package com.cubeitcorp.navit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehiculeReqUpdate {
	
	
	private String Id;
	
	private String code;

	  
	private String matricule;

	private String marque;
	
	private String type;
	
	private String filter;

}
