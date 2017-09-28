package com.academy.mongo;

import com.academy.core.domain.Section;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SectionRepository extends MongoRepository<Section, String> {

	
}
