package com.academy.core.query.result;

import com.academy.core.dto.MemberBean;

import java.util.Collections;
import java.util.List;

public class GetAcademyMembersResult implements QueryResult{

	private List<MemberBean> members= Collections.emptyList();

	public List<MemberBean> getMembers() {
		return members;
	}

	public void addMember(MemberBean member) {
		this.members.add(member);
	}

	public void setMembers(List<MemberBean> members) {
		this.members = members;
	}
	
}
