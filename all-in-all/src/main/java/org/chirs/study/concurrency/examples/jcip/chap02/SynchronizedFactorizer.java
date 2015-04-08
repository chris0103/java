package org.chirs.study.concurrency.examples.jcip.chap02;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Listing 2.6.  Servlet that caches last result, but with unacceptably poor concurrency. Don’t do this.
 */
public class SynchronizedFactorizer implements Servlet {
	
	private final AtomicReference<BigInteger> lastNumber = new AtomicReference<BigInteger>();
	private final AtomicReference<BigInteger[]> lastFactors = new AtomicReference<BigInteger[]>();

	@Override
	public void init(ServletConfig config) throws ServletException {

	}

	@Override
	public ServletConfig getServletConfig() {
		return null;
	}

	@Override
	public synchronized void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
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

	@Override
	public String getServletInfo() {
		return null;
	}

	@Override
	public void destroy() {

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
