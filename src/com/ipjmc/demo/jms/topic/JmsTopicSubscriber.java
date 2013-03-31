package com.ipjmc.demo.jms.topic;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JmsTopicSubscriber {

	public static void main(String [] args) throws Exception {
		InitialContext ctx = new InitialContext();
		TopicConnectionFactory connFactory = (TopicConnectionFactory) ctx.lookup("TopicCF");
		TopicConnection connection = connFactory.createTopicConnection();
		
		TopicSession session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
		Topic topic = (Topic) ctx.lookup("topic1");
		
		TopicSubscriber subscriber = session.createSubscriber(topic, null, true);
		
		
		subscriber.setMessageListener(new MessageListener() {
			
			private int count = 0;
			@Override
			public void onMessage(Message msg) {
				TextMessage message = (TextMessage) msg;
				if (count % 1000 == 0) {
					try {
						System.out.println(count + ": " + message.getText());
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
