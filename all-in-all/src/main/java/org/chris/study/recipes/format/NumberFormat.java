package org.chris.study.recipes.format;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class NumberFormat {
	
	public String toDecimal(double number, int precision) {
		StringBuffer pattern = new StringBuffer("000.");
		for (int i = 0; i < precision; i++) {
			pattern.append("#");
		}
		DecimalFormat format = new DecimalFormat(pattern.toString());
		format.setRoundingMode(RoundingMode.HALF_UP);
		return format.format(number);
	}
	
}
