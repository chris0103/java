package org.chris.study.algorithms.math;

public class GreatestCommonDivisor {

	public int getLowestCommonMultiple(int m, int n) {
		if (n > m) {
			int i = n;
			n = m;
			m = i;
		}
		int gcd = getGreatestCommonDivisor(m, n);
		if (gcd == 0) {
			return m;
		}
		return m * n / gcd;
	}
	
	public int getGreatestCommonDivisor(int m, int n) {
		if (n > m) {
			int i = n;
			n = m;
			m = i;
		}
		int q = 0;
		while (n != 0) {
			q = m % n;
			m = n;
			n = q;
		}
		return m;
	}
}
