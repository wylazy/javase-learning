package com.ipjmc.demo.timer;

public class PrintTask implements Runnable {
	
	private int num;
	
	public PrintTask(int num) {
		this.num = num;
	}
	
	@Override
	public void run() {
		int k = 0;
		for (int i = 0; i < num; i++) {
			for (int j = 0; j < num; j++) {
				k = i+j;
			}
		}
		System.out.println("k = " + k);
	}

}
