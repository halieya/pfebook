package com.cubeitcorp.navit.models;


import java.time.LocalTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "device") 
@Data
@NoArgsConstructor @AllArgsConstructor 
public class Device {

	@Id
	private String id;
	
	private String code;
	
	private String longitude,latitude,altitude;
	
	private int speed_gps,effective_speed;
	
	private int engine_rpm,engine_temperature;
	
	private int fuel_level,kilometrage;
	
	private String timestamp;
	
	 private LocalTime engine_on_time;
}
