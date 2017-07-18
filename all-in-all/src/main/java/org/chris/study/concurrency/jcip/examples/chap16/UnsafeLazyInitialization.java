package org.chris.study.concurrency.jcip.examples.chap16;

public class UnsafeLazyInitialization {

	private static Resource resource;
	
	public static Resource getInstace() {
		if (resource == null) {
			resource = new Resource();	// unsafe publication
		}
		return resource;
	}
}
