package com.cubeitcorp.navit.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Document(collection = "notif") 
@Data @AllArgsConstructor @NoArgsConstructor
public class Notif {
	
	@Id
	private String id;
	
	private String filter;
	
	private String title;
	
	private String desc;
	
	private String timestamp;
	
	public Notif(String filter, String title, String desc, String timestamp) {
		super();
		this.filter = filter;
		this.title = title;
		this.desc = desc;
		this.timestamp = timestamp;
	}
}
