package com.academy.mongo;

import com.academy.core.domain.Academy;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AcademyRepository extends MongoRepository<Academy, String>{

}
