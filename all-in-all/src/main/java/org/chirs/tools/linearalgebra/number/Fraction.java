package linearalgebra.number;

public class Fraction {
	
	private int numerator = 0;
	private int denominator = 1;
	
	/**
	 * Construct Fraction.
	 * @param numerator
	 * @param denominator
	 */
	public Fraction(int numerator, int denominator) {
		if (denominator == 0) {
			throw new IllegalArgumentException("Denominator cannot be zero!");
		}
		this.numerator = numerator;
		this.denominator = denominator;
	}
	
	public void pack() {
		int gcd = getGreatestCommonDivisor(Math.abs(numerator), Math.abs(denominator));
		if (gcd != 0) {
			numerator /= gcd;
			denominator /= gcd;
		}
		if (denominator < 0) {
			numerator *= -1;
			denominator *= -1;
		}
	}
	
	public Fraction negate() {
		numerator *= -1;
		return this;
	}
	
	public Fraction flip() {
		if (numerator == 0) {
			throw new IllegalStateException("Numerator is zeor, cannot be flipped!");
		}
		int temp = numerator;
		numerator = denominator;
		denominator = temp;
		return this;
	}
	
	public Fraction add(Fraction frac) {
		frac.pack();
		int lcm = getLowestCommonMultiple(Math.abs(denominator), Math.abs(frac.getDenominator()));
		numerator = numerator * (lcm / denominator) + frac.getNumerator() * (lcm / frac.getDenominator());
		denominator = lcm;
		pack();
		return this;
	}
	
	public Fraction substract(Fraction frac) {
		return add(frac.negate());
	}
	
	public Fraction multiply(Fraction frac) {
		numerator *= frac.getNumerator();
		denominator *= frac.getDenominator();
		pack();
		return this;
	}
	
	public Fraction divide(Fraction frac) {
		return multiply(frac.flip());
	}
	
	public boolean isNegative() {
		return numerator * denominator < 0;
	}
	
	@Override
	public String toString() {
		pack();
		return numerator + (denominator == 1 ? "" : "/" + denominator);
	}

	private int getLowestCommonMultiple(int m, int n) {
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
	
	private int getGreatestCommonDivisor(int m, int n) {
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

	/*
	 * Accessor methods.
	 */
	
	public int getNumerator() {
		return numerator;
	}

	public int getDenominator() {
		return denominator;
	}
	
	public static void main(String[] args) {
		Fraction frac = new Fraction(3, 4);
		System.out.println(frac.multiply(new Fraction(5, 6)));
	}
}
