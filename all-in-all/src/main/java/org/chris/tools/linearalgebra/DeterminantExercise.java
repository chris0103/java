package org.chris.tools.linearalgebra;

import java.util.Random;

import org.chris.tools.linearalgebra.number.Fraction;

public class DeterminantExercise {

	private static final int LEVEL = 3;

	private static Random random = new Random();
	
	private Fraction[][] det = new Fraction[LEVEL][LEVEL];
	
	public void generate() {
		for (int m = 0; m < LEVEL; m++) {
			for (int n = 0; n < LEVEL; n++) {
				int num = random.nextInt(100) - random.nextInt(100);
				int den = 1;
				while (den == 0) {
					den = random.nextInt(100) - random.nextInt(100);
				}
				det[m][n] = new Fraction(num, den);
			}
		}
	}
	
	public void print() {
		for (int m = 0; m < LEVEL; m++) {
			for (int n = 0; n < LEVEL; n++) {
				System.out.print(det[m][n]+ "\t\t");
			}
			System.out.println();
		}
	}
	
	public Fraction calculate() {
		return calculate(det);
	}
	
	private Fraction calculate(Fraction[][] det) {
		int len = det.length;
		if (len == 2) {
			return det[0][0].multiply(det[1][1]).substract(det[0][1].multiply(det[1][0]));
		}
		Fraction sum = new Fraction(0, 1);
		for (int n = 0; n < len; n++) {
			Fraction a = det[0][n];
			if ((n + 1) % 2 == 1) {
				a = a.negate();
			}
			Fraction[][] subDet = new Fraction[len - 1][len - 1];
			for (int mm = 1; mm < len; mm++) {
				int cc = 0;
				for (int nn = 0; nn < len; nn++) {
					if (nn != n) {
						subDet[mm - 1][cc++] = det[mm - 1][nn];
					}
				}
			}
			sum.add(calculate(subDet));
		}
		return sum;
	}
	
	public static void main(String[] args) {
		DeterminantExercise ex = new DeterminantExercise();
		ex.generate();
		ex.print();
		Fraction result = ex.calculate();
		System.out.println(result);
	}
}
