package org.chris.study.concurrency.flavors.actor;

public class Message {

	private int num;
	
	/**
	 * Constructor.
	 * @param num
	 */
	public Message(int num) {
		this.num = num;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
}
