package org.chris.study.concurrency.jcip.examples.chap02;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.chris.study.concurrency.jcip.annotations.GuardedBy;
import org.chris.study.concurrency.jcip.annotations.ThreadSafe;

/**
 * Listing 2.6.  Servlet that caches last result, but with unacceptably poor concurrency. Don’t do this.
 */
@ThreadSafe
public class SynchronizedFactorizer extends GenericServlet implements Servlet {
	
	@GuardedBy("this")
	private AtomicReference<BigInteger> lastNumber;
	
	@GuardedBy("this")
	private AtomicReference<BigInteger[]> lastFactors;

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
