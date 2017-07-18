package org.chris.study.concurrency.jcip.examples.chap03;

import java.io.IOException;
import java.math.BigInteger;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.chris.study.concurrency.jcip.annotations.ThreadSafe;

/**
 * Listing 3.13.  Caching the last result using a volatile reference to an immutable holder object.
 */
@ThreadSafe
public class VolatileCachedFactorizer extends GenericServlet implements Servlet {
	
	private volatile OneValueCache cache = new OneValueCache(null, null);

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		BigInteger i = extractFromRequest(req);
		BigInteger[] factors = cache.getFactors(i);
		if (factors == null) {
			factors = factor(i);
			cache = new OneValueCache(i, factors);
		}
		encodeIntoResponse(res, factors);
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
