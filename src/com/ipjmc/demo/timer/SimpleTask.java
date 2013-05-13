package com.ipjmc.demo.timer;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class SimpleTask implements Runnable {

	int id;

	int k = 0;
	public SimpleTask(int id) {
		this.id = id;
	}
	
	@Override
	public void run() {
		if (++k % 3 == 0) {
			int i = k/0;
		}
		System.out.println(id + " Executed " + new Date());
		
	}

}
