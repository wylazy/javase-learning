package com.ipjmc.demo.jms.broker;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.activemq.broker.Broker;
import org.apache.activemq.broker.BrokerPlugin;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.security.AuthenticationUser;
import org.apache.activemq.security.SimpleAuthenticationPlugin;

public class ActiveMQServer {

	public static void main(String [] args) throws Exception {
		BrokerService broker = new BrokerService();
		broker.setBrokerName("my-broker");
		//broker.setDataDirectory("data/");
		broker.setPersistent(false);
		
		SimpleAuthenticationPlugin authPlugin = new SimpleAuthenticationPlugin();
		List<AuthenticationUser> users = new ArrayList<AuthenticationUser>();
		users.add(new AuthenticationUser("system", "manager", "admins, publishers, consumers"));
		authPlugin.setUsers(users);
		
		broker.setPlugins(new BrokerPlugin[]{authPlugin});
		broker.addConnector("tcp://localhost:61616");
		
		broker.start();
		
		System.out.println("Press Key to stop ActiveMQ server");
		System.in.read();
		broker.stop();
	}
}
