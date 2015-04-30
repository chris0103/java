package org.chris.study.concurrency.pooling;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPool {

	private BlockingQueue<Connection> pool = new ArrayBlockingQueue<Connection>(10);
	private AtomicInteger connCount = new AtomicInteger();

	public Connection getConnection() throws ConnUnavailException, InterruptedException, SQLException {
	    Connection conn = pool.poll(5, TimeUnit.SECONDS);
	    if (conn == null) {
	        synchronized (connCount) {
	            if (connCount.get() < 10) {
	                conn = getNewConnection();
	                pool.offer(conn);
	                connCount.incrementAndGet();
	            }
	        }
	    }
	    if (conn == null) {
	    	throw new ConnUnavailException();
	    } else {
	    	return conn;
	    }
	}
	
	private Connection getNewConnection() throws SQLException {
		return DriverManager.getConnection("foo:bar");
	}
}
