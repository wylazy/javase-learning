package com.ipjmc.demo.concurrent;

public class Main {

	public static void main(String [] argv) throws InterruptedException {
		
		Shared shared = new Shared();
		new Thread(new BackgroundRunnable(shared)).start();
		
		System.out.println("count = " + shared.getCount());
	}
}
