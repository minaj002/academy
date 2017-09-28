package com.academy.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.format.datetime.joda.DateTimeFormatterFactory;

import java.util.Date;

public class DateTimeUtils {

	public static String dateToString(Date date){
		
		DateTimeFormatterFactory formatterFactory = new DateTimeFormatterFactory();
		formatterFactory.setPattern("yyyy-MM-dd");
		DateTimeFormatter formatter = formatterFactory.createDateTimeFormatter();
		DateTime dateTime = new DateTime(date);
		return dateTime.toString(formatter);	
	}
	
}
