package com.academy.rest.function;


import com.academy.rest.api.Member;
import com.academy.core.dto.MemberBean;
import org.joda.time.DateTime;

import java.util.function.Function;

public class MemberToMemberBeanFunction implements Function<Member, MemberBean> {

	@Override
	public MemberBean apply(Member member) {
		MemberBean memberBean = new MemberBean();
		
		memberBean.setFirstName(member.getFirstName());
		memberBean.setLastName(member.getLastName());
		memberBean.setDateOfBirth(DateTime.parse(member.getDateOfBirth()).toDate());
		memberBean.setJoinDate(DateTime.parse(member.getJoinDate()).toDate());
		memberBean.setStreet(member.getStreet());
		memberBean.setCity(member.getCity());
		memberBean.setEmail(member.getEmail());
		memberBean.setPhone(member.getPhone());
		memberBean.setId(member.getId());
		return memberBean;
	}

}
