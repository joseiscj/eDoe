package com.edoe.edoe.models;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "descricoes")
public class Description implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	private String description;
	
	public Description() {
		
	}
	
	public Description(String description) {
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
