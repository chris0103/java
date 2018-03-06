package org.chris.study.concurrency.jcip.examples.chap01;

import org.chris.study.concurrency.jcip.annotations.GuardedBy;
import org.chris.study.concurrency.jcip.annotations.ThreadSafe;

/**
 * Listing 1.2. Thread-safe sequence generator.
 */
@ThreadSafe
public class Sequence {
	
    @GuardedBy("this")
    private int nextValue;

    public synchronized int getNext() {
        return nextValue++;
    }
}
