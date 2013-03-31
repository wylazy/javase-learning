package com.ipjmc.demo.date;

import java.util.Calendar;

import com.ipjmc.demo.utils.DateHelper;

public class MonthDate {

	public static void main(String [] args) {
		Calendar cal = Calendar.getInstance();
		System.out.println(DateHelper.date2String(cal.getTime()));
		cal.add(Calendar.DAY_OF_YEAR, -cal.get(Calendar.DAY_OF_MONTH)+1);
		System.out.println(DateHelper.date2String(cal.getTime()));
		System.out.println(cal.get(Calendar.DAY_OF_MONTH));
	}
}
