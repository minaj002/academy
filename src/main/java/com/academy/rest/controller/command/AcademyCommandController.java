package com.academy.rest.controller.command;


import com.academy.core.command.AddAcademyCommand;
import com.academy.core.command.AddSectionToAcademyCommand;
import com.academy.core.command.result.AddAcademyResult;
import com.academy.core.command.result.AddSectionResult;
import com.academy.core.command.service.CommandService;
import com.academy.core.dto.SectionBean;
import com.academy.mapper.ObjectMapper;
import com.academy.rest.api.Academy;
import com.academy.rest.api.Section;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.RequestContext;

import java.util.function.Function;

@RestController
@RequestMapping("/academy")
public class AcademyCommandController {

	private static Logger LOG = LoggerFactory
			.getLogger(AcademyCommandController.class);

	@Autowired
	CommandService commandService;

	@Autowired
	private ObjectMapper objectMapper;

	private static Function<Academy, AddAcademyCommand> ACADEMY_TO_ADD_ACADEMY_COMMAND_FUNCTION = new AcademyToAddAcademyFunction();
	
	@RequestMapping(method = RequestMethod.POST, path = "/new")
	@ResponseBody
	public ResponseEntity<String> addNewMember(@RequestBody Academy academy) {

		AddAcademyResult result = commandService.execute(ACADEMY_TO_ADD_ACADEMY_COMMAND_FUNCTION.apply(academy));

		if (!StringUtils.isEmpty(result.getId())) {
			return new ResponseEntity<String>(HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(method = RequestMethod.POST, path = "/section")
	@ResponseBody
	public ResponseEntity<String> addSection(@RequestBody Section section) {

		String userName = getUserName();
		AddSectionResult result = commandService.execute(new AddSectionToAcademyCommand(objectMapper.getFactory().getMapperFacade().map(section, SectionBean.class), userName));

		if (!StringUtils.isEmpty(result.getId())) {
			return new ResponseEntity<String>(HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	private String getUserName() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}


	private static class AcademyToAddAcademyFunction implements
			Function<Academy, AddAcademyCommand> {

		@Override
		public AddAcademyCommand apply(Academy academy) {
			AddAcademyCommand command = new AddAcademyCommand(
					academy.getName(), academy.getEmail(), academy.getPassword());
			return command;
		}

	}

	@ExceptionHandler
	@ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public String handle(Exception exception){
		LOG.error("Exception while processing incoming request.", exception);
		return "Unexpected error!";
	}
	
}
