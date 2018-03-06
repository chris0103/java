package org.chris.study.concurrency.jcip.examples.chap03;

import org.chris.study.concurrency.jcip.annotations.GuardedBy;
import org.chris.study.concurrency.jcip.annotations.ThreadSafe;

/**
 * Listing 3.3. Thread-safe mutable integer holder.
 */
@ThreadSafe
public class SynchronizedInteger {

	@GuardedBy("this")
	private int value;
	
	public synchronized int get() {
		return value;
	}
	
	public synchronized void set(int value) {
		this.value = value;
	}
}
