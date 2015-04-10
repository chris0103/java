package org.chirs.study.concurrency.examples.jcip.chap03;

/**
 * Listing 3.15. Class at risk of failure if not properly published.
 */
public class Holder {

	private int n;
	
	public Holder(int n) {
		this.n = n;
	}
	
	public void assertSanity() {
		if (n != n) {
			throw new AssertionError("This statement is false.");
		}
	}
}
