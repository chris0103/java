package org.chris.study.concurrency.jcip.examples.chap02;

import java.math.BigInteger;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.chris.study.concurrency.jcip.annotations.GuardedBy;
import org.chris.study.concurrency.jcip.annotations.ThreadSafe;

/**
 * Listing 2.8. Servlet that caches its last request and result.
 */
@ThreadSafe
public class CachedFactorizer extends GenericServlet implements Servlet {
	
    @GuardedBy("this")
    private BigInteger lastNumber;
    
    @GuardedBy("this")
    private BigInteger[] lastFactors;
    
    @GuardedBy("this")
    private long hits;
    
    @GuardedBy("this")
    private long cacheHits;

    public synchronized long getHits() {
        return hits;
    }

    public synchronized double getCacheHitRatio() {
        return (double) cacheHits / (double) hits;
    }

    public void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = null;
        synchronized (this) {
            ++hits;
            if (i.equals(lastNumber)) {
                ++cacheHits;
                factors = lastFactors.clone();
            }
        }
        if (factors == null) {
            factors = factor(i);
            synchronized (this) {
                lastNumber = i;
                lastFactors = factors.clone();
            }
        }
        encodeIntoResponse(resp, factors);
    }

    private void encodeIntoResponse(ServletResponse resp, BigInteger[] factors) {
    	
    }

    private BigInteger extractFromRequest(ServletRequest req) {
        return new BigInteger("7");
    }

    private BigInteger[] factor(BigInteger i) {
        // Doesn't really factor
        return new BigInteger[]{i};
    }
}
