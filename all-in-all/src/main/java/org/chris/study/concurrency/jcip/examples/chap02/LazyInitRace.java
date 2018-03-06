package org.chris.study.concurrency.jcip.examples.chap02;

import org.chris.study.concurrency.jcip.annotations.NotThreadSafe;

/**
 * Listing 2.3. Race condition in lazy initialization. Don’t do this.
 */
@NotThreadSafe
public class LazyInitRace {
	
    private ExpensiveObject instance = null;

    public ExpensiveObject getInstance() {
        if (instance == null) {
            instance = new ExpensiveObject();
        }
        return instance;
    }
}

class ExpensiveObject {
	
}
