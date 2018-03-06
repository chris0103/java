package org.chris.study.concurrency.jcip.examples.chap04;

import org.chris.study.concurrency.jcip.annotations.GuardedBy;

/**
 * Listing 4.3. Guarding state with a private lock.
 */
public class PrivateLock {
	
    private final Object myLock = new Object();
    
    @GuardedBy("myLock") 
    Widget widget;

    void someMethod() {
        synchronized (myLock) {
            // Access or modify the state of widget
        }
    }
}

class Widget {
	
}
