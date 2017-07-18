package org.chris.study.concurrency.jcip.examples.chap03;

import java.math.BigInteger;
import java.util.Arrays;

import org.chris.study.concurrency.jcip.annotations.Immutable;

/**
 * Listing 3.12. Immutable holder for caching a number and its factors.
 */
@Immutable
public class OneValueCache {

	private final BigInteger lastNumber;
	private final BigInteger[] lastFactors;
	
	public OneValueCache(BigInteger i, BigInteger[] factors) {
		lastNumber = i;
		lastFactors = Arrays.copyOf(factors, factors.length);
	}
	
	public BigInteger[] getFactors(BigInteger i) {
		if (lastNumber == null || !lastNumber.equals(i)) {
			return null;
		} else {
			return Arrays.copyOf(lastFactors, lastFactors.length);
		}
	}
}
