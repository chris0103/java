package org.chris.study.concurrency.mutex.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TaskRunner {

	private Map<Class<? extends Runnable>, Lock> mLocks = new HashMap<>();
	
	public void runTaskUniquely(Runnable r, int secondsToWait) throws InterruptedException {
		Lock lock = getLock(r.getClass());
		boolean acquired = lock.tryLock(secondsToWait, TimeUnit.SECONDS);
		if (acquired) {
			try {
				r.run();
			} finally {
				lock.unlock();
			}
		}
	}
	
	private synchronized Lock getLock(Class<? extends Runnable> clazz) {
		Lock lock = mLocks.get(clazz);
        if (lock == null) {
            lock = new ReentrantLock();
            mLocks.put(clazz, lock);
        }
        return lock;
	}
}
