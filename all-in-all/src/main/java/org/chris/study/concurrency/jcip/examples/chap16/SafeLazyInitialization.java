package org.chris.study.concurrency.jcip.examples.chap16;

public class SafeLazyInitialization {

	private static Resource resource;
	
	public synchronized static Resource getInstace() {
		if (resource == null) {
			resource = new Resource();
		}
		return resource;
	}
}
