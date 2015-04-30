package org.chris.study.jmx.mbean;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

public class MeanManager {

	public static void main(String[] args) throws Exception {
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		ObjectName name = new ObjectName("org.chris.study.jmx.mbean:type=Echo");
		Echo mbean = new Echo();
		mbs.registerMBean(mbean, name);
		mbs.invoke(name, "print", new Object[] {"Chris"}, new String[] {"java.lang.String"});
		Thread.sleep(Long.MAX_VALUE);
	}
}
