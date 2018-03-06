package org.chris.study.concurrency.jcip.examples.chap03;

/**
 * Listing 3.14.  Publishing an object without adequate synchronization.  Don’t do this.
 */
public class UnsafePublication {

	public Holder holder;
	
	public void initialize() {
		holder = new Holder(42);
	}
}
