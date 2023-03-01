package org.chris.study.mq.rabbitmq;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ConnectionUtil {

	 /**
     * 建立与RabbitMQ的连接
     * @return
     * @throws Exception
     */
    public static Connection getConnection() throws Exception {
        //定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置服务地址
        factory.setHost("127.0.0.1");
        //端口
        factory.setPort(5672);
        
        //设置账号信息，用户名、密码、vhost
        factory.setVirtualHost("/LAPTOP-FN72CQV8");//设置虚拟机，一个mq服务可以设置多个虚拟机，每个虚拟机就相当于一个独立的mq
        factory.setUsername("rabbit");
        factory.setPassword("");
        // 通过工厂获取连接
        Connection connection = factory.newConnection();
        return connection;
    }
    
    public static void main(String[] args) throws Exception {
    	Connection connection = ConnectionUtil.getConnection();
    	System.out.println(connection);
    }
}
