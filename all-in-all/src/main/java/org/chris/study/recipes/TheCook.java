package org.chris.study.recipes;

import org.chris.study.recipes.format.NumberFormat;

public class TheCook {

	public void cook() {
		NumberFormat format = new NumberFormat();
		System.out.println(format.toDecimal(4.4166665, 2));
	}
	
	public static void main(String[] args) {
		new TheCook().cook();
	}
}
