package com.academy.core.command.handler;

import com.academy.core.command.result.AddMemberResult;
import com.academy.core.domain.AcademyUser;
import com.academy.core.command.AddMemberCommand;
import com.academy.core.domain.Member;
import com.academy.core.dto.MemberBean;
import com.academy.core.function.MemberBeanToMemberFunction;
import com.academy.mongo.AcademyUserRepository;
import com.academy.mongo.MemberRepository;
import com.academy.utils.DateTimeUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class AddMemberHandler implements CommandHandler<AddMemberCommand, AddMemberResult> {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    AcademyUserRepository academyUserRepository;

    private static final Function<MemberBean, Member> MEMBER_BEAN_TO_MEMBER = new MemberBeanToMemberFunction();

    @Override
    public AddMemberResult execute(AddMemberCommand command) {

	    AcademyUser user = academyUserRepository.findByName(command.getUserName());
        MemberBean memberBean = command.getMember();
        Member member = MEMBER_BEAN_TO_MEMBER.apply(memberBean);
        member.setAcademyName(user.getAcademy().getName());
        memberRepository.save(member);

	    return new AddMemberResult(member.getId());
    }

}
