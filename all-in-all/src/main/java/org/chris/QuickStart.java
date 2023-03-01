package org.chris;

import java.util.Stack;

public class QuickStart {

	private static Stack<String> stack = new Stack<String>();
	
	
	
	public static void main(String[] args) {
		
		
		double size = 50.0;
		double total = 0;
		while (size > 0.1) {
			total += size;
			size = size * 3 / 4;
		}
		System.out.println("Total = " + total + ", size = " + size + ".");
		
		
	}
}
