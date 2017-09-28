package com.academy.rest.api;

import com.academy.core.domain.Section;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

public class Academy extends ResourceSupport {
	
	private String name;

	private String email;
	
	private String password;

	private List<Section> sections;

	public String getName()  {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}
}
