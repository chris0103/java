package org.chris.study.concurrency.jcip.examples.chap01;

import org.chris.study.concurrency.jcip.annotations.NotThreadSafe;

/**
 * Listing 1.1. Non-thread-safe sequence generator.
 */
@NotThreadSafe
public class UnsafeSequence {
	
    private int value;

    /**
     * Returns a unique value.
     */
    public int getNext() {
        return value++;
    }
}
