package com.academy.core.command.handler;


import com.academy.core.domain.AcademyUser;
import com.academy.core.command.AddAcademyCommand;
import com.academy.core.command.result.AddAcademyResult;
import com.academy.core.domain.Academy;
import com.academy.mongo.AcademyRepository;
import com.academy.mongo.AcademyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class AddAcademyHandler implements CommandHandler<AddAcademyCommand, AddAcademyResult> {

	@Autowired
	AcademyRepository academyRepository;
	
	@Autowired
	AcademyUserRepository academyUserRepository;
	
//    @Autowired
//    private PasswordEncoder encoder;
	
	private static final Function<AddAcademyCommand, Academy> ADD_ACADEMY_COMMAND_TO_ACADEMY = new AddAcademyCommandToAcademyFunction();
	
	@Override
	public AddAcademyResult execute(AddAcademyCommand command) {

		Academy academy = ADD_ACADEMY_COMMAND_TO_ACADEMY.apply(command);
		academy=academyRepository.save(academy);
		
		AcademyUser academyUser = new AcademyUser();
		academyUser.setAcademy(academy);
		academyUser.setActive(true);
		academyUser.setName(academy.getEmail());
		academyUser.setPassword(command.getPassword());
//		academyUser.setPassword(encoder.encode(command.getPassword()));
		academyUser.addRole(AcademyUser.Role.ROLE_OWNER);
		
		academyUserRepository.save(academyUser );
		
		return new AddAcademyResult(academy.getId());
	}

	
	static class AddAcademyCommandToAcademyFunction implements Function<AddAcademyCommand, Academy>{

		@Override
		public Academy apply(AddAcademyCommand command) {
			Academy academy = new Academy();
			academy.setName(command.getName());
			academy.setEmail(command.getEmail());
			
			return academy;
		}
		
	}
	
}
