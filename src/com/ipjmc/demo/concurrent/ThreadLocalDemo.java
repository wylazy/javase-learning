package com.ipjmc.demo.concurrent;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadLocalDemo {

	private static ThreadLocal<SimpleOjb> threadLocal = new ThreadLocal<SimpleOjb>() {
		protected SimpleOjb initialValue() {
			return new SimpleOjb();
		};
	};
	
	
	public static void main(String [] args) {
		
		ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);
		for (int i = 0; i < 5; i++) {
			executor.scheduleAtFixedRate(new Runnable() {
				
				@Override
				public void run() {
					SimpleOjb sp = threadLocal.get();
					int k = ++sp.a;
					System.out.println("k = " + k);
				}
			}, 0, 1, TimeUnit.SECONDS);
		}
	}
	
	static class SimpleOjb {
		int a;
	}
}
