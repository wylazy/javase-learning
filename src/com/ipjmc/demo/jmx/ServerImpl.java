package com.ipjmc.demo.jmx;

public class ServerImpl implements Server {

	private long startTime;
	
	public ServerImpl() {
		startTime = System.currentTimeMillis();
	}
	
	@Override
	public long getStartTime() {
		return startTime;
	}

	
}
