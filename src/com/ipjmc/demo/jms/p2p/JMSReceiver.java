package com.ipjmc.demo.jms.p2p;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;

public class JMSReceiver {

	public static final String URL = "tcp://localhost:61616";
	
	public static void main(String [] args) throws Exception {
	
		InitialContext ctx = new InitialContext();
		QueueConnectionFactory connectionFactory = (QueueConnectionFactory) ctx.lookup("QueueCF");
		QueueConnection connection = connectionFactory.createQueueConnection("system", "manager");
		
		QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		Queue rQueue = (Queue) ctx.lookup("my-queue");
		QueueReceiver receiver = session.createReceiver(rQueue);
		receiver.setMessageListener(new MessageListener() {
			
			int count = 0;
			@Override
			public void onMessage(Message msg) {
				
				TextMessage message = (TextMessage) msg;
				if (count % 100 == 0) {
					try {
						System.out.println(message.getText());
					} catch (JMSException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				count++;
			}
		});
		
		connection.start();
	}
}
