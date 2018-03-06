package org.chris.study.concurrency.jcip.examples.chap04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.chris.study.concurrency.jcip.annotations.NotThreadSafe;
import org.chris.study.concurrency.jcip.annotations.ThreadSafe;

/**
 * Listing 4.14. Non-thread-safe attempt to implement put-if-absent. Don’t do this.
 */
@NotThreadSafe
class BadListHelper <E> {
	
    public List<E> list = Collections.synchronizedList(new ArrayList<E>());

    public synchronized boolean putIfAbsent(E x) {
        boolean absent = !list.contains(x);
        if (absent)
            list.add(x);
        return absent;
    }
}

/**
 * Listing 4.15. Implementing put-if-absent with client-side locking.
 */
@ThreadSafe
class GoodListHelper <E> {
    public List<E> list = Collections.synchronizedList(new ArrayList<E>());

    public boolean putIfAbsent(E x) {
        synchronized (list) {
            boolean absent = !list.contains(x);
            if (absent)
                list.add(x);
            return absent;
        }
    }
}
