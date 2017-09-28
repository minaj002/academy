package com.academy.core.query.handler;

import com.academy.core.domain.Academy;
import com.academy.core.dto.AcademyBean;
import com.academy.core.function.AcademyToAcademyBeanFunction;
import com.academy.core.query.GetAcademiesQuery;
import com.academy.core.query.result.GetAcademiesResult;

import com.academy.mongo.AcademyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class GetAcademiesHandler implements	QueryHandler<GetAcademiesQuery, GetAcademiesResult> {

	@Autowired
	AcademyRepository academyRepository;
	
	private Function<Academy, AcademyBean> ACADEMY_TO_ACADEMY_BEAN_FUNCTION = new AcademyToAcademyBeanFunction();
	
	@Override
	public GetAcademiesResult execute(GetAcademiesQuery query) {

		List<Academy> academies = academyRepository.findAll();
		
		List<AcademyBean> academyBeans = academies.stream().map(ACADEMY_TO_ACADEMY_BEAN_FUNCTION).collect(Collectors.toList());
		
		return new GetAcademiesResult(academyBeans);
	}

}
