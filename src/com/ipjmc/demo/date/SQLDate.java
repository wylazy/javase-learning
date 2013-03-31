package com.ipjmc.demo.date;

import java.sql.Date;

public class SQLDate {

	public static void main(String [] args) {
		Date date = new Date(System.currentTimeMillis());
		System.out.println(date.toLocaleString());
	}
}
