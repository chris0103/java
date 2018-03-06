package org.chris.study.concurrency.jcip.examples.chap03;

/**
 * Listing 3.4. Counting sheep.
 */
public class SheepCounter {

	private volatile static boolean asleep;
	
	private static int sheepCount = 1;
	
	private static void countSomeSheep() {
		System.out.println(sheepCount++ + " sheep...");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("Ahh! Where was it??");
		}
	}
	
	private static class CountThread extends Thread {
		
		public void run() {
			while (!asleep) {
				countSomeSheep();
			}
			System.out.println("Dream, sweet dream... zzZZ...");
		}
	}
	
	public static void main(String[] args) throws Exception {
		new CountThread().start();
		while (Math.random() <= 0.95) {
			Thread.sleep(1000);
		}
		asleep = true;
	}
}
