package org.chirs.study.concurrency.examples.jcip.chap16;

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
