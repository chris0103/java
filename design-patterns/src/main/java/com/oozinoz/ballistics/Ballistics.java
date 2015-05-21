package com.oozinoz.ballistics;



public class Ballistics {

	private static BallisticsFunction rate;
	private static BallisticsFunction thrust;
	
	public static BallisticsFunction rate() {
		if (rate == null) {
			rate = new BallisticsFunction() {

				public double function(double t, double tPeak) {
					return 0.5 * Math.pow(25, -Math.pow((t - tPeak), 2));
				}
				
			};
		}
		return rate;
	}
	
	public static BallisticsFunction thrust() {
		if (thrust == null) {
			thrust = new BallisticsFunction() {
				
				public double function(double t, double tPeak) {
					return 1.7 * Math.pow(rate().function(t, tPeak) / 0.6, 1 / 0.3);
				}
			};
		}
		return thrust;
	}
}
