package org.chris.study.mq.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JMSDemo {

	ConnectionFactory connectionFactory;

	Connection connection;

	Session session;

	Destination destination;

	MessageProducer producer;

	MessageConsumer consumer;

	Message message;

	boolean useTransaction = false;

	public void demo() throws JMSException, NamingException {
		try {
			Context ctx = new InitialContext();
			connectionFactory = (ConnectionFactory) ctx.lookup("ConnectionFactoryName");
			// 使用ActiveMQ时：connectionFactory = new ActiveMQConnectionFactory(user, password, getOptimizeBrokerUrl(broker));
			connection = connectionFactory.createConnection();
			connection.start();
			session = connection.createSession(useTransaction, Session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue("TEST.QUEUE");
			// 生产者发送消息
			producer = session.createProducer(destination);
			message = session.createTextMessage("this is a test");
	
			// 消费者同步接收
			consumer = session.createConsumer(destination);
			message = (TextMessage) consumer.receive(1000);
			System.out.println("Received message: " + message);
			// 消费者异步接收
			consumer.setMessageListener(new MessageListener() {
				@Override
				public void onMessage(Message message) {
					if (message != null) {
						doMessageEvent(message);
					}
				}
			});
		} catch(JMSException e) {
	
		} finally {
			producer.close();
			session.close();
			connection.close();
		}
	}

	protected void doMessageEvent(Message message) {
		
	}
}
