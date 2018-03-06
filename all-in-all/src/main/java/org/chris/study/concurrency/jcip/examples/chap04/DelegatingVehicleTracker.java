package org.chris.study.concurrency.jcip.examples.chap04;

import java.awt.Point;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.chris.study.concurrency.jcip.annotations.ThreadSafe;

/**
 * Listing 4.7. Delegating thread safety to a ConcurrentHashMap.
 */
@ThreadSafe
public class DelegatingVehicleTracker {
	
    private final ConcurrentMap<String, Point> locations;
    private final Map<String, Point> unmodifiableMap;

    public DelegatingVehicleTracker(Map<String, Point> points) {
        locations = new ConcurrentHashMap<String, Point>(points);
        unmodifiableMap = Collections.unmodifiableMap(locations);
    }

    public Map<String, Point> getLocations() {
        return unmodifiableMap;
    }

    public Point getLocation(String id) {
        return locations.get(id);
    }

    public void setLocation(String id, int x, int y) {
        if (locations.replace(id, new Point(x, y)) == null)
            throw new IllegalArgumentException("invalid vehicle name: " + id);
    }
    
    // Listing 4.8. Returning a static copy of the location set instead of a “live” one.
    public Map<String, Point> getLocationsAsStatic() {
    	return Collections.unmodifiableMap(new HashMap<String, Point>(locations));
    }
}
