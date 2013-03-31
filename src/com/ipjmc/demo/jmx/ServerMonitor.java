package com.ipjmc.demo.jmx;

public class ServerMonitor implements ServerMonitorMBean {

	private final Server server;
	
	public ServerMonitor(Server server) {
		this.server = server;
	}
	
	@Override
	public long getUpTime() {
		return System.currentTimeMillis() - server.getStartTime();
	}

}
