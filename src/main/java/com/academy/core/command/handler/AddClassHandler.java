package com.academy.core.command.handler;


import com.academy.core.command.result.AddClassResult;
import com.academy.core.domain.AcademyUser;
import com.academy.core.command.AddClassCommand;
import com.academy.core.domain.ClassAttended;
import com.academy.core.domain.Member;
import com.academy.core.dto.MemberBean;
import com.academy.core.function.MemberBeanToMemberFunction;
import com.academy.mongo.AcademyUserRepository;
import com.academy.mongo.ClassAttendedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class AddClassHandler implements CommandHandler<AddClassCommand, AddClassResult> {

	@Autowired
	ClassAttendedRepository classAttendedRepository;
	
	@Autowired
	AcademyUserRepository academyUserRepository;
	
	private static Function<MemberBean, Member> MEMBER_BEAN_TO_MEMBER_FUNCTION = new MemberBeanToMemberFunction();
	
	@Override
	public AddClassResult execute(AddClassCommand command) {
		
		
		ClassAttended classAttended = toClassAttended(command);
		
		classAttended=classAttendedRepository.save(classAttended);
		
		return new AddClassResult(classAttended.getId());
	}

	private ClassAttended toClassAttended(AddClassCommand command) {
		ClassAttended classAttended= new ClassAttended();
		classAttended.setDate(command.getDate());
		
		AcademyUser user = academyUserRepository.findByName(command.getUserName());
		classAttended.setAcademy(user.getAcademy());
		
		List<Member> members =command.getMembers().stream().map(MEMBER_BEAN_TO_MEMBER_FUNCTION).collect(Collectors.toList());
		classAttended.setMembers(members);
		return classAttended;
	}

}
