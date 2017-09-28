package com.academy.core.dto;

import com.academy.core.domain.Section;

import java.util.List;

public class AcademyBean {

	private String name;

	private String email;

	private List<Section> sections;

	public String getName() {
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

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}
}
