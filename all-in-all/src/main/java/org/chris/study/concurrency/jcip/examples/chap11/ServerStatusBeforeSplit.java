package org.chris.study.concurrency.jcip.examples.chap11;

import java.util.HashSet;
import java.util.Set;

import org.chris.study.concurrency.jcip.annotations.GuardedBy;
import org.chris.study.concurrency.jcip.annotations.ThreadSafe;

/**
 * Listing 11.7. ServerStatus refactored to use split locks.
 */
@ThreadSafe
public class ServerStatusBeforeSplit {
	
	@GuardedBy("users")
	public final Set<String> users = new HashSet<String>();
	
	@GuardedBy("queries")
	public final Set<String> queries = new HashSet<String>();
	
	public void addUser(String u) {
		synchronized (users) {
			users.add(u);
		}
	}
	
	public void addQuery(String q) {
		synchronized (queries) {
			queries.add(q);
		}
	}
}
