package org.chris.study.concurrency.flavors.actor;

import java.util.concurrent.atomic.AtomicInteger;

public class Result {

	private AtomicInteger count = new AtomicInteger();

	/**
	 * Constructor.
	 * @param sum
	 */
	public Result(AtomicInteger sum) {
		this.count = sum;
	}

	public AtomicInteger getSum() {
		return count;
	}

	public void setSum(AtomicInteger sum) {
		this.count = sum;
	}
}
