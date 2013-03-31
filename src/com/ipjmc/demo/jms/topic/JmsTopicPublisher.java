package com.ipjmc.demo.jms.topic;

import static com.ipjmc.demo.jms.p2p.JMSReceiver.URL;

import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.InitialContext;


public class JmsTopicPublisher {

	public static final int SIZE = 1024;
	public static final String MSG;
	static {
		StringBuilder sb = new StringBuilder();
		while (sb.length() < SIZE) {
			sb.append("ZeroMQ是一个很有个性的项目，它原来是定位为“史上最快消息队列”，所以名字里面有“MQ”两个字母，但是后来逐渐演变发展，慢慢淡化了消息队列的身影，改称为 ...");
		}
		MSG = sb.substring(0, SIZE);
	}
	
	public static void main(String [] args) throws Exception {

		InitialContext ctx = new InitialContext();
		TopicConnectionFactory connFactory = (TopicConnectionFactory) ctx.lookup("TopicCF");
		TopicConnection connection = connFactory.createTopicConnection();
		
		TopicSession session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
		Topic topic = (Topic) ctx.lookup("topic1");
		
		TopicPublisher publisher = session.createPublisher(topic);
		
		connection.start();
		
		long time = System.currentTimeMillis();
		for (int i = 0; i < 400000; i++) {
			writeMessage(session, publisher, MSG);
		}
		System.out.println(System.currentTimeMillis() - time);
		session.close();
		connection.close();
	}
	
	public static void writeMessage(TopicSession session, TopicPublisher publisher, String text) throws JMSException {
		TextMessage message = session.createTextMessage();
		message.setText(text);
		publisher.publish(message);
	}
}
