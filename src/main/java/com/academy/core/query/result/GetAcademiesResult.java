package com.academy.core.query.result;

import com.academy.core.dto.AcademyBean;

import java.util.List;

public class GetAcademiesResult implements QueryResult {

	private List<AcademyBean> academies;

	public GetAcademiesResult(List<AcademyBean> academies) {
		this.academies = academies;
	}

	public List<AcademyBean> getAcademies() {
		return academies;
	}

	public void setAcademies(List<AcademyBean> academies) {
		this.academies = academies;
	}
	
}
