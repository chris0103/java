package org.chris.study.concurrency.jcip.examples.chap16;

public class DoubleCheckedLocking {

	private static Resource resource;
	
	public static Resource getInstance() {
		if (resource == null) {
			synchronized (DoubleCheckedLocking.class) {
				if (resource == null) {
					resource = new Resource();
				}
			}
		}
		return resource;
	}
}
