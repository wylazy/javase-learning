package com.ipjmc.demo.concurrent;

class BackgroundRunnable implements  Runnable{

	private Shared shared;
	
	public BackgroundRunnable(Shared shared) {
		this.shared = shared;
	}
	
	@Override
	public void run() {
		shared.working(181);
	}
	
}
