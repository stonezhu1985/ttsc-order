package com.ttsc.data.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static String getDateFormatter(Date date,String format){
		DateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}
	

	public static Date addDays(int days){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_YEAR, days);
		return calendar.getTime();
	}
	
	
	public static String addDaysFormat(int days){
		return getDateFormatter(addDays(days),"yyyy-MM-dd");
	}
	
	
	public static String addDaysFormat(int days,String format){
		return getDateFormatter(addDays(days),format);
	}
	
}
