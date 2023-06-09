package com.cubeitcorp.navit.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "vehicle") 
@Data @NoArgsConstructor @AllArgsConstructor 
public class Vehicle {

	@Id
	private String id;
	
    @Indexed(unique = true , direction = IndexDirection.DESCENDING)
	private String code;

    @Indexed(unique = true , direction = IndexDirection.DESCENDING)
	private String matricule;

	private String marque;
	
	private String type;
	
	private String filter;


}