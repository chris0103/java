package org.chirs.study.concurrency.examples.jcip.chap03;

/**
 * Listing 3.3. Thread-safe mutable integer holder.
 */
public class SynchronizedInteger {

	private int value;
	
	public synchronized int get() {
		return value;
	}
	
	public synchronized void set(int value) {
		this.value = value;
	}
}
