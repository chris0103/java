package org.chris.study.jmx.mxbean;

public interface ServerMXBean {

    public ServerConfigure getServerConfigure();
    
    public void setServerConfigure(ServerConfigure serverConfigure);
       
    public void defaultServerConfigure();
}
