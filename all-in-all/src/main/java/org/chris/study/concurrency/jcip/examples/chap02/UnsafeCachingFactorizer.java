package org.chris.study.concurrency.jcip.examples.chap02;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.chris.study.concurrency.jcip.annotations.NotThreadSafe;

/**
 * Listing 2.5. Servlet that attempts to cache its last result without adequate atomicity. Don’t do this.
 */
@NotThreadSafe
public class UnsafeCachingFactorizer extends GenericServlet implements Servlet {
	
	private final AtomicReference<BigInteger> lastNumber = new AtomicReference<BigInteger>();
	private final AtomicReference<BigInteger[]> lastFactors = new AtomicReference<BigInteger[]>();

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		BigInteger i = extractFromRequest(req);
		if (i.equals(lastNumber.get())) {
			encodeIntoResponse(res, lastFactors.get());
		} else {
			BigInteger[] factors = factor(i);
			lastNumber.set(i);
			lastFactors.set(factors);
			encodeIntoResponse(res, factors);
		}
	}
	
	private BigInteger extractFromRequest(ServletRequest req) {
		// do the extraction
		return null;
	}

	private void encodeIntoResponse(ServletResponse res, BigInteger[] bigIntegers) {
		// do the encoding...
	}

	private BigInteger[] factor(BigInteger i) {
		// do the factoring
		return null;
	}
}
