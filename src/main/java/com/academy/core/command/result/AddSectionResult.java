package com.academy.core.command.result;

public class AddSectionResult implements CommandResult{

	private String id;

	public AddSectionResult(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}	
}
