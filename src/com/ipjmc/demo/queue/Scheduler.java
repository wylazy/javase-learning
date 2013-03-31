package com.ipjmc.demo.queue;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * 
 * @author wylazy
 *
 */
public class Scheduler {

	private static Scheduler mScheduler = new Scheduler();
	private PriorityBlockingQueue<SelScanner> mSelScannerQueue;
	
	private Scheduler() {
		mSelScannerQueue = new PriorityBlockingQueue<SelScanner>(32, new SelScanner.SelScannerComparator());
	}
	
	public static Scheduler getInstance() {
		return mScheduler;
	}

	public PriorityBlockingQueue<SelScanner> getQueue() {
		return mSelScannerQueue;
	}
	
	public static void main(String [] args) throws InterruptedException {
		
		PriorityBlockingQueue<SelScanner> queue = getInstance().getQueue();
		queue.add(new SelScanner(20, 5));
		queue.add(new SelScanner(21, 3));
		queue.add(new SelScanner(22, 2));
		queue.add(new SelScanner(20, 4));
		queue.add(new SelScanner(20, 1));
		
		System.out.println("CPU num = " + Runtime.getRuntime().availableProcessors());
		for (int i = 0; i < 5; i++) {
			SelScanner selScanner = queue.take();
			System.out.printf("%d: %d\n", selScanner.getScannerId(), selScanner.getJobId());
		}
	}
}