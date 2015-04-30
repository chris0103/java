package org.chris.study.jmx.mbean;

public class Echo implements EchoMBean {

	@Override
	public void print(String yourName) {
		System.out.println("Hi, " + yourName + "!");
	}

}
