package com.academy.core.domain;

import org.springframework.data.mongodb.core.index.Indexed;

import java.math.BigDecimal;
import java.util.Date;

public class Section {

	@Indexed
	private String id;
	
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
