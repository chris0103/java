package org.chris.study.concurrency.jcip.examples.chap16;

public class EagerInitialization {

	private static Resource resource = new Resource();
	
	public static Resource getResource() {
		return  resource;
	}
}
