package com.academy.rest.controller.query;

import com.academy.core.dto.MemberBean;
import com.academy.core.query.GetAcademyMembersQuery;
import com.academy.core.query.result.GetAcademyMembersResult;
import com.academy.core.query.service.QueryService;
import com.academy.mapper.ObjectMapper;
import com.academy.rest.api.Member;
import com.academy.rest.function.MemberBeanToMemberFunction;
import ma.glasnost.orika.MapperFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/members")
public class MembersQueryController {

	private static Logger LOG = LoggerFactory
			.getLogger(MembersQueryController.class);
	
	private static Function<MemberBean, Member> MEMBER_BEAN_TO_MEMBER_FUNCTION = new MemberBeanToMemberFunction();
	
	@Autowired
	QueryService queryService;
	@Autowired
	private ObjectMapper objectMapper;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Collection<Member> getAllMembers() {
		
		String name = getUserName();


		MapperFacade facade = objectMapper.getFactory().getMapperFacade();


		LOG.debug("Getting members for ", name);
		GetAcademyMembersQuery query = new GetAcademyMembersQuery(name);
		GetAcademyMembersResult members = queryService.execute(query);
		
		List<Member> membersJson = members.getMembers().stream().map(memberBean -> facade.map(memberBean, Member.class)).collect(Collectors.toList());
//		List<Member> membersJson = members.getMembers().stream().map(MEMBER_BEAN_TO_MEMBER_FUNCTION).collect(Collectors.toList());

		return membersJson;
	}

	private String getUserName() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
	
}
