package com.academy.core.function;

import com.academy.core.domain.ClassAttended;
import com.academy.core.domain.Member;
import com.academy.core.dto.ClassAttendedBean;
import com.academy.core.dto.MemberBean;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ClassAttendedToClassAttendedBeanFunction implements Function<ClassAttended, ClassAttendedBean> {

    private Function<Member, MemberBean> MEMBER_TO_MEMBER_BEAN_FUNCTION = new MemberToMemberBeanFunction();

    @Override
    public ClassAttendedBean apply(ClassAttended from) {

	ClassAttendedBean bean = new ClassAttendedBean();
	bean.setDate(from.getDate());
	List<MemberBean> membersAttended = from.getMembers().stream().map(MEMBER_TO_MEMBER_BEAN_FUNCTION).collect(Collectors.toList());
	bean.setMembers(membersAttended);

	return bean;
    }

}
