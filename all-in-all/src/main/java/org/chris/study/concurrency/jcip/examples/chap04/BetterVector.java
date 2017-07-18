package org.chris.study.concurrency.jcip.examples.chap04;

import java.util.Vector;

import org.chris.study.concurrency.jcip.annotations.ThreadSafe;


/**
 * Listing 4.13. Extending Vector to have a put-if-absent method.
 */
@ThreadSafe
public class BetterVector <E> extends Vector<E> {
	
    // When extending a serializable class, you should redefine serialVersionUID
    static final long serialVersionUID = -3963416950630760754L;

    public synchronized boolean putIfAbsent(E x) {
        boolean absent = !contains(x);
        if (absent)
            add(x);
        return absent;
    }
}
