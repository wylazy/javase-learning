package com.ipjmc.demo.timer;

import java.util.concurrent.TimeUnit;

public class SimpleTask implements Runnable {

	int id;

	public SimpleTask(int id) {
		this.id = id;
	}
	
	@Override
	public void run() {
		long k = 0;
		for (int i = 0; i < 100000; i++) {
			for (int j = 0; j < 100000; j++) {
				 k += i+j;
			}
		}
		
		System.out.println(id + " Executed " + k);
		
	}

}
