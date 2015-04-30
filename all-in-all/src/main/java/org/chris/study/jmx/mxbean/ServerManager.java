package org.chris.study.jmx.mxbean;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

public class ServerManager {

    public static void main(String[] args) throws Exception {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ServerConfigure serverConfigure = new ServerConfigure(8080, "localhost", 20, 100);
        Server server = new Server(serverConfigure);
        ObjectName serverName = new ObjectName("org.chris.study.jmx.mxbean:type=Server");
        mbs.registerMBean(server, serverName);
        System.out.println("Waiting...");
        Thread.sleep(Long.MAX_VALUE);
    }   
}
