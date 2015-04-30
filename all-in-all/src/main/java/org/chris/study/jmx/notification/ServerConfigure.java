package org.chris.study.jmx.notification;

import java.util.concurrent.atomic.AtomicLong;

import javax.management.AttributeChangeNotification;
import javax.management.NotificationBroadcasterSupport;

public class ServerConfigure extends NotificationBroadcasterSupport implements ServerConfigureMBean {

    private int port;
    private String host;
    private AtomicLong sequenceNumber = new AtomicLong(1);
  
    @Override  
    public void setPort(int port) {
        int oldPort = this.port;
        this.port = port;
        AttributeChangeNotification notification = new AttributeChangeNotification(
        		this, sequenceNumber.getAndIncrement(), System.currentTimeMillis(), AttributeChangeNotification.ATTRIBUTE_CHANGE, "Server Port Change", "java.lang.Integer", oldPort + "", this.port + "");
        sendNotification(notification);
    }
  
    @Override  
    public void setHost(String host) {
        String oldHost = this.host;
        this.host = host;
        AttributeChangeNotification notification = new AttributeChangeNotification( 
                this, sequenceNumber.getAndIncrement(), System.currentTimeMillis(), AttributeChangeNotification.ATTRIBUTE_CHANGE, "Server Host Change", "java.lang.String", oldHost, this.host);
        sendNotification(notification);
    }
  
    @Override  
    public int getPort() {
        return port;
    }

    @Override
    public String getHost() {
        return host;
    }
}
