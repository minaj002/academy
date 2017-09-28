package com.academy.core.query.result;

import com.academy.core.dto.ClassAttendedBean;

import java.util.Collections;
import java.util.List;

public class GetClassesForDateResult implements QueryResult {

	private List<ClassAttendedBean> classesAttended= Collections.emptyList();

	public GetClassesForDateResult(List<ClassAttendedBean> classesAttended) {
		this.classesAttended = classesAttended;
	}

	public List<ClassAttendedBean> getClassesAttended() {
		return classesAttended;
	}
	
}
