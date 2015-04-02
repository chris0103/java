package org.chirs.study.concurrency.examples.jcip.chap16;

public class EagerInitialization {

	private static Resource resource = new Resource();
	
	public static Resource getResource() {
		return  resource;
	}
}
