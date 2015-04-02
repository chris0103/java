package org.chirs.study.concurrency.examples.jcip.chap03;

/**
 * Listing 3.1. Sharing variables without synchronization. Don’t do this.
 */
public class NoVisibility {
	
	private static boolean ready;
	private static int number;
	
	private static class ReaderThread extends Thread {
		
		public void run() {
			while (!ready) {
				Thread.yield();
			}
			System.out.println(number);
		}
	}
	
	public static void main(String[] args) {
		new ReaderThread().start();
		number = 42;
		ready = true;
	}
}