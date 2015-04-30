package org.chris.study.jmx.notification;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

public class NotificationManager {

	public static void main(String[] args) throws Exception {
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		ObjectName name = new ObjectName("org.chris.study.jmx.notification:type=ServerConfigure");
		ServerConfigure mbean = new ServerConfigure();
		mbs.registerMBean(mbean, name);
		ServerConfigureNotificationListener listener = new ServerConfigureNotificationListener();
		mbs.addNotificationListener(name, listener, null, null);
		Thread.sleep(Long.MAX_VALUE);
	}
	
}
