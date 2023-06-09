package com.cubeitcorp.navit.dto;

import org.springframework.beans.factory.annotation.Value;

public interface VehicleDTO {

	   @Value("#{target.filter}")
	    String getFilter();

	   @Value("#{target.matricule}")
	   String getMatricule();
}
