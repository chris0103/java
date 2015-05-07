package com.oozinoz.utility;

public class Dollars {

	private static final int CENTS_PER_DOLLAR = 100;
	
	private long cents;

    public Dollars(double value) {
        this.cents = Math.round(value * CENTS_PER_DOLLAR);
    }
    
    public String toString() {
        StringBuffer result = new StringBuffer("$");
        long dollars = cents / CENTS_PER_DOLLAR;
        result.append(dollars);
        result.append('.');
        long pennies = cents % CENTS_PER_DOLLAR;
        if (pennies < 10) {
        	result.append('0');
        }
        result.append(pennies);
        return result.toString();
    }
}
