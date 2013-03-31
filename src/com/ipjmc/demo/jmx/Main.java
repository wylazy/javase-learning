package com.ipjmc.demo.jmx;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

public class Main {

	public static ObjectName objectName;
	public static MBeanServer mBeanServer;
	
	public static void init() throws Exception {
		Server server = new ServerImpl();
		ServerMonitor monitor = new ServerMonitor(server);
		mBeanServer = MBeanServerFactory.createMBeanServer();
		objectName = new ObjectName("objectName:id=ServerMonitor1");
		mBeanServer.registerMBean(monitor, objectName);
	}
	
	public static void manage() throws Exception {
		long upTime = (Long) mBeanServer.getAttribute(objectName, "UpTime");
		System.out.println(upTime);
	}
	
	public static void main(String [] args) throws Exception {
		init();
		TimeUnit.SECONDS.sleep(1);
		manage();
	}
}
