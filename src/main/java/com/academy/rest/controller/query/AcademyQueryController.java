package com.academy.rest.controller.query;

import com.academy.core.dto.AcademyBean;
import com.academy.core.query.GetAcademiesQuery;
import com.academy.core.query.GetAcademyQuery;
import com.academy.core.query.result.GetAcademiesResult;
import com.academy.core.query.service.QueryService;
import com.academy.mapper.ObjectMapper;
import com.academy.rest.api.Academy;
import com.academy.rest.function.AcademyBeanToAcademyFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/academies")
public class AcademyQueryController {

	private static Logger LOG = LoggerFactory
			.getLogger(AcademyQueryController.class);
	
	private static Function<AcademyBean, Academy> ACADEMY_BEAN_TO_ACADEMY_FUNCTION = new AcademyBeanToAcademyFunction();
	
	@Autowired
	QueryService queryService;

	@Autowired
	private ObjectMapper objectMapper;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Academy> getAllAcademies() {
		
		GetAcademiesQuery query = new GetAcademiesQuery();
		GetAcademiesResult academies = queryService.execute(query);
		
		List<Academy> academyJson = academies.getAcademies().stream().map(ACADEMY_BEAN_TO_ACADEMY_FUNCTION).collect(Collectors.toList());
		
		return academyJson;
	}


	@RequestMapping(method = RequestMethod.GET, path = "/me")
	@ResponseBody
	public Academy getAcademy() {

		String name = getUserName();

		GetAcademyQuery query = new GetAcademyQuery();
		query.setName(name);
		GetAcademiesResult academies = queryService.execute(query);

		Optional<Academy> academyJson = academies.getAcademies()
				.stream().map(academy -> objectMapper.getFacade().map(academy, Academy.class )).findFirst();

		return academyJson.get();
	}

	private String getUserName() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}

}
