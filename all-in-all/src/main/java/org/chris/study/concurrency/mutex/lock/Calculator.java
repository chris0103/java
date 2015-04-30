package org.chris.study.concurrency.mutex.lock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Calculator {

	private int value;
	private int calculatedVale;
	private ReadWriteLock lock = new ReentrantReadWriteLock();
	
	public Calculator(int value) {
		lock.writeLock().lock();
		try {
			this.value = value;
			this.calculatedVale = doMySlowCalculation(value);
		} finally {
			lock.writeLock().unlock();
		}
	}
	
	private int doMySlowCalculation(int value) {
		return Math.getExponent(Math.pow(Math.random() * value, 3));
	}

	public int getValue() {
		lock.readLock().lock();
		try {
			return value;
		} finally {
			lock.readLock().unlock();
		}
	}

	public int getCalculatedVale() {
		lock.readLock().lock();
		try {
			return calculatedVale;
		} finally  {
			lock.readLock().unlock();
		}
	}
}
