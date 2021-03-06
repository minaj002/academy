package com.academy.rest.api;

import java.util.Collections;
import java.util.List;

public class ClassAttended {

	private String date;
	
	private String id;
	
	private List<Member> members= Collections.emptyList();

	public String getDate() {
		return date;
	}

	public List<Member> getMembers() {
		return members;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

	public String getId() {
	    return id;
	}

	public void setId(String id) {
	    this.id = id;
	} 
	
}
