package com.academy.rest.function;

import com.academy.core.dto.ClassAttendedBean;
import com.academy.core.dto.MemberBean;
import com.academy.rest.api.ClassAttended;
import com.academy.rest.api.Member;
import com.academy.utils.DateTimeUtils;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ClassAttendedBeanToClassAttendedFunction implements
		Function<ClassAttendedBean, ClassAttended> {

	private static Function<MemberBean, Member> MEMBER_BEAN_TO_MEMBER_FUNCTION = new MemberBeanToMemberFunction();
	
	@Override
	public ClassAttended apply(ClassAttendedBean from) {
	
		ClassAttended classAttended = new ClassAttended();
		List<Member> members = from.getMembers().stream().map(MEMBER_BEAN_TO_MEMBER_FUNCTION).collect(Collectors.toList());
		classAttended.setMembers(members);
		classAttended.setDate(DateTimeUtils.dateToString(from.getDate()));
		
		return classAttended;
	}

}
