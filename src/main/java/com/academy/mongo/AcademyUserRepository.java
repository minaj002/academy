package com.academy.mongo;

import com.academy.core.domain.AcademyUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AcademyUserRepository extends MongoRepository<AcademyUser, String>{

	AcademyUser findByName(String academyUsername);
	
}
