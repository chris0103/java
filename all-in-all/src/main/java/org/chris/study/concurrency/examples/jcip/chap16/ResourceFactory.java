package org.chris.study.concurrency.examples.jcip.chap16;

public class ResourceFactory {

	private static class ResourceHolder {
		
		public static Resource resource = new Resource();
	}
	
	public static Resource getResource() {
		return ResourceHolder.resource;
	}
}
