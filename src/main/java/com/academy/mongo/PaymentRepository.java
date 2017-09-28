package com.academy.mongo;

import com.academy.core.domain.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface PaymentRepository extends MongoRepository<Payment, String> {

    List<Payment> findByMemberIdAndDateBetweenOrderByPaidUntillDesc(String memberId, Date start, Date end);

    List<Payment> findByAcademyNameAndDateBetween(String academyName, Date start, Date end);

}
