package org.chirs.study.concurrency.examples.jcip.chap03;

/**
 * Listing 3.2. Non-thread-safe mutable integer holder.
 */
public class MutableInteger {

	private int value;
	
	public int get() {
		return value;
	}
	
	public void set(int value) {
		this.value = value;
	}
}
