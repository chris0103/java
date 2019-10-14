package org.chris.tools.randomania;

import java.util.Random;

public class RandomBasic {
	
	private static Random random = new Random();

	public int randomNumber(int scope) {
		return random.nextInt(scope) + 1;
	}
	
	public static void main(String[] args) {
		RandomBasic random = new RandomBasic();
		System.out.println(random.randomNumber(476));
	}
}
