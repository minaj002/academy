package com.academy.core.security;

import com.academy.core.domain.AcademyUser;
import com.academy.mongo.AcademyUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

import static com.academy.core.domain.AcademyUser.Role.ROLE_ADMIN;

@Component
public class DataInitializer {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	AcademyUserRepository academyUserRepository;

//	@Autowired
//	PasswordEncoder passwordEncoder;

	@PostConstruct
	public void init() {

		logger.info("Initializing Default data");

		if (academyUserRepository.count() == 0) {

			AcademyUser user = new AcademyUser();
			user.setActive(true);
			user.setName("JevBjj@mail.com");
			user.setPassword("password");
//			user.setPassword(passwordEncoder.encode("password"));

			user.addRole(ROLE_ADMIN);

			 academyUserRepository.save(user);
		}
		else{
			List<AcademyUser> users = academyUserRepository.findAll();
			for (AcademyUser academyUser : users) {
				logger.info("User: " + academyUser.getName());
				logger.info("Password: " + academyUser.getPassword());
			}
			logger.info("Default data already initialized");
		}
	}

}
