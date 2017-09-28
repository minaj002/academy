package com.academy.rest.function;

import com.academy.core.dto.MemberBean;
import com.academy.rest.api.Member;
import com.academy.rest.api.Section;

import java.util.function.Function;
import java.util.stream.Collectors;

import static com.academy.utils.DateTimeUtils.dateToString;

public class MemberBeanToMemberFunction implements Function<MemberBean, Member> {

	@Override
	public Member apply(MemberBean bean) {
		Member member= new Member();
		member.setId(bean.getId());
		member.setCity(bean.getCity());
		member.setFirstName(bean.getFirstName());
		member.setLastName(bean.getLastName());
		member.setStreet(bean.getStreet());
		member.setPhone(bean.getPhone());
		member.setEmail(bean.getEmail());
		member.setDateOfBirth(dateToString(bean.getDateOfBirth()));
		member.setJoinDate(dateToString(bean.getJoinDate()));
		member.setSections(bean.getSections().stream().map(sectionBean -> {
			Section section = new Section();
			section.setId(sectionBean.getId());
			section.setName(sectionBean.getName());
			return section;
		}).collect(Collectors.toList()));
		return member;
	}
}
