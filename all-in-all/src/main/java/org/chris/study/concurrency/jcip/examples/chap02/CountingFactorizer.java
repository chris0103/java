package org.chris.study.concurrency.jcip.examples.chap02;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.chris.study.concurrency.jcip.annotations.ThreadSafe;

/**
 * Listing 2.4. Servlet that counts requests using AtomicLong.
 */
@ThreadSafe
public class CountingFactorizer extends GenericServlet implements Servlet {
	
    private final AtomicLong count = new AtomicLong(0);

    public long getCount() {
    	return count.get();
    }

    public void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = factor(i);
        count.incrementAndGet();
        encodeIntoResponse(resp, factors);
    }

    private void encodeIntoResponse(ServletResponse res, BigInteger[] factors) {
    	
    }
    
    private BigInteger extractFromRequest(ServletRequest req) {
    	return null;
    }
    
    private BigInteger[] factor(BigInteger i) {
    	return null;
    }
}
