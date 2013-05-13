package com.ipjmc.demo.timer;

import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduleTask {

	private ScheduledThreadPoolExecutor executor;
	private HashMap<Integer, Future> taskMap;
	
	public ScheduleTask() {
		executor = new ScheduledThreadPoolExecutor(1);
		taskMap = new HashMap<Integer, Future>();
	}
	
	public ScheduledThreadPoolExecutor getExecutor() {
		return this.executor;
	}
	
	public void start() {
		
		for (int i = 0; i < 8; i++) {
			int id = i+1;
			SimpleTask taks = new SimpleTask(id);
			Future future = executor.scheduleAtFixedRate(taks, 0, 1, TimeUnit.SECONDS);
			
			taskMap.put(id, future);
		}
		
	}
	
	public boolean removeTaks(int id) {
		Future future = taskMap.get(id);
		
		if (future != null) {
			System.out.println("Get Task [" + id + "] successfully");
			return future.cancel(true);
			//return executor.remove(task);
		}
		
		System.out.println("Get Task [" + id + "] failed");
		return false;
	}
	
	public static void main(String [] args) {
		ScheduleTask service = new ScheduleTask();
		service.start();
		Scanner scanner = new Scanner(System.in);
		int id = 8;
		
		while (id > 0) {
			id = scanner.nextInt();
			service.getExecutor().schedule(new PrintTask(id), 0, TimeUnit.SECONDS);
		}
		
		scanner.close();
	}
}
