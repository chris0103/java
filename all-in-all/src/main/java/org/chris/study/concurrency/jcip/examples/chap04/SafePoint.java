package org.chris.study.concurrency.jcip.examples.chap04;

import org.chris.study.concurrency.jcip.annotations.GuardedBy;
import org.chris.study.concurrency.jcip.annotations.ThreadSafe;

/**
 * Listing 4.11. Thread-safe mutable point class.
 */
@ThreadSafe
public class SafePoint {
	
    @GuardedBy("this")
    private int x, y;

    private SafePoint(int[] a) {
        this(a[0], a[1]);
    }

    public SafePoint(SafePoint p) {
        this(p.get());
    }

    public SafePoint(int x, int y) {
        this.set(x, y);
    }

    public synchronized int[] get() {
        return new int[] {x, y};
    }

    public synchronized void set(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
