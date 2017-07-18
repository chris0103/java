package org.chris.study.concurrency.jcip.examples.chap03;

import java.util.HashSet;
import java.util.Set;

import org.chris.study.concurrency.jcip.annotations.Immutable;

/**
 * Listing 3.11. Immutable class built out of mutable underlying objects.
 */
@Immutable
public final class ThreeStooges {
	
	private final Set<String> stooges = new HashSet<String>();
	
	public ThreeStooges() {
		stooges.add("Moe");
		stooges.add("Larry");
		stooges.add("Curly");
	}
	
	public boolean isStooge(String name) {
		return stooges.contains(name);
	}
}
