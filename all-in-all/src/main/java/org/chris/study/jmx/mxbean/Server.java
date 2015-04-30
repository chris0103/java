package org.chris.study.jmx.mxbean;

public class Server implements ServerMXBean {

	private ServerConfigure serverConfigure;
    
    public Server(ServerConfigure serverConfigure) { 
        this.serverConfigure = serverConfigure;
    }
       
    @Override  
    public ServerConfigure getServerConfigure() { 
        synchronized (serverConfigure) { 
            return this.serverConfigure;
        }
    }
       
    @Override  
    public void setServerConfigure(ServerConfigure serverConfigure) { 
        synchronized (serverConfigure) { 
            this.serverConfigure = serverConfigure;
        }
    }
       
    @Override  
    public void defaultServerConfigure() { 
        synchronized (serverConfigure) { 
            serverConfigure.setPort(80);
            serverConfigure.setHost("localhost");
            serverConfigure.setMinThread(10);
            serverConfigure.setMaxThread(500);
        }
    }
}
