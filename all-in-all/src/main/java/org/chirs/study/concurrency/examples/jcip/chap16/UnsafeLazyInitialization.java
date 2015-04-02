package org.chirs.study.concurrency.examples.jcip.chap16;

public class UnsafeLazyInitialization {

	private static Resource resource;
	
	public static Resource getInstace() {
		if (resource == null) {
			resource = new Resource();	// unsafe publication
		}
		return resource;
	}
}
