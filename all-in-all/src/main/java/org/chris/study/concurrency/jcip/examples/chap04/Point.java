package org.chris.study.concurrency.jcip.examples.chap04;

import org.chris.study.concurrency.jcip.annotations.Immutable;

/**
 * Listing 4.6. Immutable Point class used by DelegatingVehicleTracker.
 */
@Immutable
public class Point {
	
    public final int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
