package com.ipjmc.demo.date;

import java.util.Calendar;

import com.ipjmc.demo.utils.DateHelper;

public class WeekDate {

	public static void main(String [] args) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		System.out.println(DateHelper.date2String(cal.getTime()));
		cal.add(Calendar.DAY_OF_YEAR, -(cal.get(Calendar.DAY_OF_WEEK)+5)%7);
		System.out.println(DateHelper.date2String(cal.getTime()));
		for (int i = 0; i < 7; i++) {
			System.out.println(cal.get(Calendar.DAY_OF_WEEK));
			cal.add(Calendar.DAY_OF_YEAR, 1);
		}
		
	}
}
