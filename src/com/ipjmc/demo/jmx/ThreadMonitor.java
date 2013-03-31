package com.ipjmc.demo.jmx;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class ThreadMonitor {

	public static void main(String [] args) throws Exception {
		ThreadMXBean threadMx = ManagementFactory.getThreadMXBean();
		
		System.out.println(threadMx.getThreadCount());
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, "Customed000").start();
		
		Thread.sleep(100);
		System.out.println(threadMx.getThreadCount());
		for (long id : threadMx.getAllThreadIds()) {
			System.out.println("Thread[" + id + "] = " + threadMx.getThreadInfo(id).getThreadName());
		}
	}
}
