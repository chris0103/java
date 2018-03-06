package org.chris.study.concurrency.jcip.examples.chap02;

/**
 * Listing 2.7. Code that would deadlock if intrinsic locks were not reentrant.
 */
public class LoggingWidget extends Widget {

	public synchronized void doSomething() {
		System.out.println(toString() + ": calling doSomething");
		super.doSomething();
	}
}

class Widget {
	
	public synchronized void doSomething() {
		
	}
}
