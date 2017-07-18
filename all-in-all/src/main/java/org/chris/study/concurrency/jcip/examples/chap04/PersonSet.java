package org.chris.study.concurrency.jcip.examples.chap04;

import java.util.HashSet;
import java.util.Set;

import org.chris.study.concurrency.jcip.annotations.GuardedBy;
import org.chris.study.concurrency.jcip.annotations.ThreadSafe;

/**
 * Listing 4.2. Using confinement to ensure thread safety.
 */
@ThreadSafe
public class PersonSet {
	
    @GuardedBy("this")
    private final Set<Person> mySet = new HashSet<Person>();

    public synchronized void addPerson(Person p) {
        mySet.add(p);
    }

    public synchronized boolean containsPerson(Person p) {
        return mySet.contains(p);
    }

    interface Person {
    	
    }
}
