package com.ipjmc.demo.jms.p2p;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;

import static com.ipjmc.demo.jms.p2p.JMSReceiver.URL;

import org.apache.activemq.ActiveMQConnectionFactory;


public class JMSSender {

	private static final String MSG;
	private static final int SIZE = 512;
	static {
		StringBuilder sb = new StringBuilder();
		while (sb.length() < SIZE) {
			sb.append("ZeroMQ是一个很有个性的项目，它原来是定位为“史上最快消息队列”，所以名字里面有“MQ”两个字母，但是后来逐渐演变发展，慢慢淡化了消息队列的身影，改称为 ...");
		}
		MSG = sb.substring(0, SIZE);
	}
	public static void main(String [] args) throws Exception {
		InitialContext ctx = new InitialContext();
		QueueConnectionFactory connectionFactory = (QueueConnectionFactory) ctx.lookup("QueueCF");
		QueueConnection connection = connectionFactory.createQueueConnection("system", "manager");
		
		QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		Queue sQueue = (Queue) ctx.lookup("my-queue");
		QueueSender sender = session.createSender(sQueue);
		
		sender.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		//sender.setDeliveryMode(DeliveryMode.PERSISTENT);
		
		connection.start();
		long time = System.currentTimeMillis();
		for (int i = 0; i < 100; i++) {
			TextMessage message = session.createTextMessage(MSG);
			sender.send(message);
		}
		
		System.out.println(System.currentTimeMillis() - time);
		session.close();
		connection.close();
	}
}
