package com.academy.core.command;

import com.academy.core.command.result.AddSectionResult;
import com.academy.core.dto.SectionBean;

public class AddSectionToAcademyCommand implements Command<AddSectionResult>{

	private SectionBean section;
	private String userName;

	public AddSectionToAcademyCommand(SectionBean section, String userName) {
		this.section = section;
		this.userName = userName;
	}

	public SectionBean getSection() {
		return section;
	}

	public String getUserName() {
		return userName;
	}
}
