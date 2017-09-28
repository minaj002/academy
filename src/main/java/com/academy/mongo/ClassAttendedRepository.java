package com.academy.mongo;

import com.academy.core.domain.Academy;
import com.academy.core.domain.ClassAttended;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface ClassAttendedRepository extends MongoRepository<ClassAttended, String> {

    List<ClassAttended> findByAcademyAndDateIsBetween(Academy academy, Date dateStart, Date end);

    List<ClassAttended> findByAcademy(Academy academy);

}
