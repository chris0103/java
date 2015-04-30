package org.chris.study.jmx.mxbean;

import java.beans.ConstructorProperties;

public class ServerConfigure {
	
	private int port;
    private String host;
    private int maxThread;
    private int minThread;
      
    @ConstructorProperties({"port", "host", "maxThread", "minThread"})
    public ServerConfigure(int port, String host, int maxThread, int minThread) {
        this.port = port;
        this.host = host;
        this.maxThread = maxThread;
        this.minThread = minThread;
    }
  
    public int getPort() {
        return port;
    }
  
    public void setPort(int port) {
        this.port = port;
    }
  
    public String getHost() {
        return host;
    }
  
    public void setHost(String host) {
        this.host = host;
    }
  
    public int getMaxThread() {
        return maxThread;
    }
  
    public void setMaxThread(int maxThread) {
        this.maxThread = maxThread;
    }
  
    public int getMinThread() {
        return minThread;
    }
  
    public void setMinThread(int minThread) {
        this.minThread = minThread;
    }
}
