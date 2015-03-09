package org.chirs.study.concurrency.flavors.actor;

import java.util.concurrent.atomic.AtomicInteger;

public class Message {

	private int num;
	private AtomicInteger sum = new AtomicInteger();
	
	/**
	 * Constructor.
	 * @param num
	 * @param sum
	 */
	public Message(int num, AtomicInteger sum) {
		this.num = num;
		this.sum = sum;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public AtomicInteger getSum() {
		return sum;
	}

	public void setSum(AtomicInteger sum) {
		this.sum = sum;
	}
}
