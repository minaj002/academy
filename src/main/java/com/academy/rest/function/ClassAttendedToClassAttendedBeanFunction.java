package com.academy.rest.function;


import com.academy.core.dto.ClassAttendedBean;
import com.academy.rest.api.ClassAttended;
import org.joda.time.DateTime;

import java.util.function.Function;

public class ClassAttendedToClassAttendedBeanFunction implements Function<ClassAttended, ClassAttendedBean> {

    @Override
    public ClassAttendedBean apply(ClassAttended from) {

	ClassAttendedBean classAttended = new ClassAttendedBean();
	classAttended.setDate(DateTime.parse(from.getDate()).toDate());

	return classAttended;
    }

}
