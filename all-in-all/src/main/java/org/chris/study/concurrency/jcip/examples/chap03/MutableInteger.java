package org.chris.study.concurrency.jcip.examples.chap03;

import org.chris.study.concurrency.jcip.annotations.NotThreadSafe;

/**
 * Listing 3.2. Non-thread-safe mutable integer holder.
 */
@NotThreadSafe
public class MutableInteger {

	private int value;
	
	public int get() {
		return value;
	}
	
	public void set(int value) {
		this.value = value;
	}
}
