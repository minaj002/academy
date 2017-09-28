package com.academy.mongo;

import com.academy.core.domain.Member;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MemberRepository extends MongoRepository<Member, String> {

	List<Member> findByAcademyName(String academyName);
	
}
