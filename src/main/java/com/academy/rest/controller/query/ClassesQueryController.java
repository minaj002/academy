package com.academy.rest.controller.query;


import com.academy.core.dto.ClassAttendedBean;
import com.academy.core.query.result.GetClassesForDateResult;
import com.academy.core.query.service.QueryService;
import com.academy.core.query.GetClassesForDateQuery;
import com.academy.rest.api.ClassAttended;
import com.academy.rest.function.ClassAttendedBeanToClassAttendedFunction;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/classes")
public class ClassesQueryController {

	private static Logger LOG = LoggerFactory
			.getLogger(ClassesQueryController.class);
	
	private static Function<ClassAttendedBean, ClassAttended> CLASS_ATTENDED_BEAN_TO_CLASS_ATTENDED_FUNCTION = new ClassAttendedBeanToClassAttendedFunction();
	
	@Autowired
	QueryService queryService;

	@RequestMapping(method = RequestMethod.GET, value = "/{date}")
	@ResponseBody
	public List<ClassAttended> getAllClasses(@PathVariable String date) {
		
		String name = getUserName();
		LOG.debug("Getting classes for ", name);
		GetClassesForDateQuery query = new GetClassesForDateQuery(name,DateTime.parse(date).toDate());
		GetClassesForDateResult classes = queryService.execute(query);
		
		List<ClassAttended>classessJson = classes.getClassesAttended().stream().map(CLASS_ATTENDED_BEAN_TO_CLASS_ATTENDED_FUNCTION).collect(Collectors.toList());
		return classessJson;
	}

	private String getUserName() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}

}
