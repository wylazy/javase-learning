package com.ipjmc.demo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {

	public static String date2String(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}
}
