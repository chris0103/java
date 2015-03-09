package org.chirs.study.concurrency.flavors.actor;

import java.util.concurrent.atomic.AtomicInteger;

public class Result {

	private AtomicInteger sum = new AtomicInteger();

	/**
	 * Constructor.
	 * @param sum
	 */
	public Result(AtomicInteger sum) {
		this.sum = sum;
	}

	public AtomicInteger getSum() {
		return sum;
	}

	public void setSum(AtomicInteger sum) {
		this.sum = sum;
	}
}
