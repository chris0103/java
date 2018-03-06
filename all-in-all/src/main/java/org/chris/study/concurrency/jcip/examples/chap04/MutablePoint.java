package org.chris.study.concurrency.jcip.examples.chap04;

import org.chris.study.concurrency.jcip.annotations.NotThreadSafe;

/**
 * Listing 4.5. Mutable point class similar to java.awt.Point.
 */
@NotThreadSafe
public class MutablePoint {
	
    public int x, y;

    public MutablePoint() {
        x = 0;
        y = 0;
    }

    public MutablePoint(MutablePoint p) {
        this.x = p.x;
        this.y = p.y;
    }
}
